package com.example.utilsdk.okhttp;

/**
 * Created by admin on 2018-09-30.
 */

import com.example.utilsdk.okhttp.https.HttpsUtils;
import com.example.utilsdk.okhttp.listener.DisposeDataHandle;
import com.example.utilsdk.okhttp.response.CommonJsonCallback;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 请求发送 请求参数配置 https支持
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT =30;
    private static OkHttpClient mOkHttpClient;

    //配置client参数
    static {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true);

        //https配置
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        //https支持
        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory(), new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        });

        //生成client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送请求
     * @param request
     * @param commonCallback
     * @return
     */
    public static Call sendRequest(Request request, CommonJsonCallback commonCallback){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonCallback);

        return call;
    }

    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
