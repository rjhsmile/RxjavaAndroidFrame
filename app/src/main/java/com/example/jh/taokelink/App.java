package com.example.jh.taokelink;

import android.app.Application;

/**
 * Created by jh on 2018/3/1.
 */

public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public synchronized static App getInstance() {
        if (mApp == null) {
            mApp = new App();
        }
        return mApp;
    }
}
