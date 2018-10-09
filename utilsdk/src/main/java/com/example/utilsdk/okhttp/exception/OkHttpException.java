package com.example.utilsdk.okhttp.exception;

/**
 * Created by www10 on 2017/4/16.
 * 自定义异常类
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;

    private int ecode;//异常码

    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }
}
