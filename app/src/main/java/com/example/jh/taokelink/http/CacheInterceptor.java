package com.example.jh.taokelink.http;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者： Created by${raojianhui}
 * 日期：2019/1/31  14:14.
 * 项目：FrameDemo
 * 包名：com.example.jh.taokelink.http
 * 描述：网络缓存
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkAvailable()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isNetworkAvailable()) {
            int maxAge = 0;
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder().header("Cache-Control", "public, max-age=" + maxAge).build();
        } else {
            // 无网络时，设置超时为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).build();
        }
        return response;
    }
}
