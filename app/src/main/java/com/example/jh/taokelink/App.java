package com.example.jh.taokelink;

import android.app.Application;
import android.content.Context;

/**
 * Created by jh on 2018/3/1.
 */

public class App extends Application {

    private static App mApp;
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
    }


    public static Context getContext() {
        return instance;
    }

    public synchronized static App getInstance() {
        if (mApp == null) {
            mApp = new App();
        }
        return mApp;
    }
}
