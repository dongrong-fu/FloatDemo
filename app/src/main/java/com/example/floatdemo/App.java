package com.example.floatdemo;

import android.app.Application;

/**
 * Created by dongrong.fu on 2019/11/14
 */
public class App extends Application {

    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        FloatWindowUtil.initFloatWindow();
    }

    public static Application getInstance(){
        return mApplication;
    }

    public static Application getAppContext(){
        return mApplication;
    }

}
