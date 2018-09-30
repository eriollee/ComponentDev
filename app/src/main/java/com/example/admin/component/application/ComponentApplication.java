package com.example.admin.component.application;

import android.app.Application;

/**
 * Created by admin on 2018-09-29.
 */

public class ComponentApplication extends Application {

    private static ComponentApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
//        initShareSDK();
//        initJPush();
//        initAdSDK();
    }

    public static ComponentApplication getInstance() {
        return mApplication;
    }

//    public void initShareSDK() {
//        ShareManager.initSDK(this);
//    }
//
//    private void initJPush() {
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//    }
//
//    private void initAdSDK() {
//        AdSDKManager.init(this);
//    }
}
