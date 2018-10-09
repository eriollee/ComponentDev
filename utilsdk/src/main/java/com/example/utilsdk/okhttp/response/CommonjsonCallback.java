package com.example.utilsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.example.utilsdk.okhttp.exception.OkHttpException;
import com.example.utilsdk.okhttp.listener.DisposeDataHandle;
import com.example.utilsdk.okhttp.listener.DisposeDataListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by www10 on 2017/4/16.
 *
 * @function 专门处理JSON的回调响应
 */

public class CommonjsonCallback implements Callback {
    //与服务器返回的字段的一个对应关系
    protected final String RESULT_CODE = "ecode";   //有返回则对于http请求来说是成功的
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private Handler mDeliveryHandler;   //进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonjsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(Call call, final IOException ioexception) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    //真正的响应处理函数
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {

                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     */
    private void handleResponse(Object responseObj) {
        //为了保持代码的健壮性,判断数据
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            Gson gson = new Gson();

            //开始尝试解析json
            if (result.has(RESULT_CODE)) {
                //从json对象中取出响应码，若0，则是正确响应(与服务器商定)
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    //不需要解析，直接返回数据到应用
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        //即，需要将JOSN对象转化为实体对象
                        Object obj = gson.fromJson(String.valueOf(result), mClass);
                        //表明正确的转为了实体对象
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            //返回的不是合法的json
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    //将服务器返回的异常回调到应用去处理
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(
                            RESULT_CODE
                    )));
                }
            }

        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
