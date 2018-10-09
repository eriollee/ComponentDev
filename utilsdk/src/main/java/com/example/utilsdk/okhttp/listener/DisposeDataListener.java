package com.example.utilsdk.okhttp.listener;

/**
 * Created by www10 on 2017/4/16.
 * 自定义事件监听
 */

public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调时间处理
     */
    public void onFailure(Object reasonObj);

}
