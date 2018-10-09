package com.example.utilsdk.okhttp.listener;

/**
 * Created by www10 on 2017/4/16.
 */

public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener,
                             Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }
}
