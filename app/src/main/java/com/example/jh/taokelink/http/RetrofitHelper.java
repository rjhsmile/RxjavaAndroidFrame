package com.example.jh.taokelink.http;

import com.example.jh.taokelink.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/4/3.
 * OkHttpClient和Retrofit结合使用更简单
 */

public class RetrofitHelper {

    private int TIME = 10;
    private static RetrofitHelper retrofitHelper;
    private OkHttpClient okHttpClient = null;

    public static RetrofitHelper getInstance() {
        if (null == retrofitHelper) {
            synchronized (RetrofitHelper.class) {
                if (null == retrofitHelper) {
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    public <T> T createApi(Class<T> clazz) {
        return createApi(clazz, Constants.BASE_URL);
    }

    public <T> T createApi(Class<T> clazz, String baseUrl) {

        /* File cacheFile = new File(App.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);*/

        //LOG打印
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ParamsInterceptor())//公共参数添加拦截器
                .addInterceptor(logInterceptor)//日志打印拦截器
                //.cache(cache).addInterceptor(new CacheInterceptor())//设置缓存拦截器
                //.cookieJar(new JavaNetCookieJar(cookieManager))//设置cookie
                .connectTimeout(TIME, TimeUnit.SECONDS)//设置超时
                .readTimeout(TIME, TimeUnit.SECONDS)
                .writeTimeout(TIME, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//错误重连
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)//设置OkHttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                .addConverterFactory(GsonConverterFactory.create())  //Json 转换器
                .build();

        return retrofit.create(clazz);
    }

    /**
     * 接口取消
     */
    public void CancelHttp() {
        okHttpClient.dispatcher().cancelAll();
    }

}
