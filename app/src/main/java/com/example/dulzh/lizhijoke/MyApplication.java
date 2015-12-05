package com.example.dulzh.lizhijoke;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wyouflf on 15/10/28.定义全局变量，舒适化xutils ，new变量
 */
public class MyApplication extends Application {
    public static String API_KEY = "873a8e53180cf53e8687097cdad4b8a5";
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);


    }
}
