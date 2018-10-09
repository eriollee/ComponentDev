package com.example.utilsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by admin on 2018-09-30.
 *
 */


/**
 * @function生成PostRequest对象
 */
public class CommonRequest {
    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if(params !=null){
            for(Map.Entry<String,String >entry:params.urlParams.entrySet()){
                //请求参数遍历添加到构建对象
                mFormBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }

        //真正请求体对象
        FormBody mFormBody = mFormBodyBuild.build();

        return new Request.Builder().url(url).post(mFormBody).build();
    }


    /**
     *
     * @param url
     * @param params
     * @return 返回get类型请求
     */
    public static Request creatGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params !=null){
            for(Map.Entry<String,String >entry:params.urlParams.entrySet()){
                //请求参数遍历添加到构建对象
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();
    }
}
