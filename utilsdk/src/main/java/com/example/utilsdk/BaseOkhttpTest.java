package com.example.utilsdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.utilsdk.okhttp.CommonOkHttpClient;
import com.example.utilsdk.okhttp.listener.DisposeDataHandle;
import com.example.utilsdk.okhttp.listener.DisposeDataListener;
import com.example.utilsdk.okhttp.request.CommonRequest;
import com.example.utilsdk.okhttp.response.CommonJsonCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by www10 on 2017/4/16.
 */

public class BaseOkhttpTest extends AppCompatActivity{

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    /**
//     * 用OKhttp发送一个最基本的请求
//     */
//
//    private void sendRequest() {
//
//        //创建okHttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        //创建一个Request
//        final Request request = new Request.Builder()
//                .url("https://www.imooc.com/")
//                .build();
//        //new call
//        Call call = mOkHttpClient.newCall(request);
//        //请求加入调度
//        call.enqueue(new Callback() {
//
//            //request failure
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            //request success
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
//
//    }
//
//    private void test(){
//        CommonOkHttpClient.sendRequest(CommonRequest.creatGetRequest("http://www.baidu.com",null),
//                new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                })));
//    }
}
