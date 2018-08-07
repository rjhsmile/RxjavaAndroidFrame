package com.example.jh.taokelink;

import android.app.Application;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

/**
 * Created by jh on 2018/3/1.
 */

public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        initNohttp();
        super.onCreate();
    }

    public synchronized static App getInstance() {
        if (mApp == null) {
            mApp = new App();
        }
        return mApp;
    }

    /**
     * 初始化NOHttp
     * 资料：http://doc.nohttp.net
     */
    private void initNohttp() {
        //初始化网络 NoHttp默认初始化。
//        NoHttp.initialize(this);
        // 开启NoHttp调试模式。
        Logger.setDebug(true);
        // 设置NoHttp打印Log的TAG。
        Logger.setTag("NoHttpTag");

        //初始化并设置默认值，默认全局超时时间是10s。
        NoHttp.initialize(this, new NoHttp.Config()
                .setConnectTimeout(6 * 1000) // 全局连接超时时间，单位毫秒。
                .setReadTimeout(6 * 1000) // 全局服务器响应超时时间，单位毫秒。
                .setCacheStore(new DBCacheStore(this)// 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                        .setEnable(true)) // 如果不使用缓存，设置setEnable(false)禁用。
                .setCookieStore(new DBCookieStore(this)// 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现。
                        .setEnable(true)) // 如果不维护cookie，设置false禁用。
                .setNetworkExecutor(new URLConnectionNetworkExecutor()) // 使用HttpURLConnection做网络层。
        );
    }
}
