package com.example.jh.taokelink.http;

import android.util.Log;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.BuildConfig;
import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.net.HttpCacheInterceptor;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/4/3.
 */

public class RetrofitHelper {

    private static final int TIME = 10;

    public static <T> T createApi(Class<T> clazz) {
        return createApi(clazz, Constants.BASE_URL);
    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("OKHttp-----", message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(App.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIME,TimeUnit.SECONDS)
                .readTimeout(TIME, TimeUnit.SECONDS)
                .connectTimeout(TIME, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                //错误重连
                //.retryOnConnectionFailure(true)
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }
}
