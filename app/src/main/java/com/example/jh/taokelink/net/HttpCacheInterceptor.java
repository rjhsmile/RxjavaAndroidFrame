package com.example.jh.taokelink.net;

import android.util.Log;

import com.example.jh.taokelink.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者： Created by${raojianhui}
 * 日期：2018/8/10  15:34.
 * 项目：FrameDemo
 * 包名：com.example.jh.taokelink.net
 * 描述：
 */

public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkAvailable()) {  //没网强制从缓存读取
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            Log.d("Okhttp", "no network");
        }

        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isNetworkAvailable()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }

}
