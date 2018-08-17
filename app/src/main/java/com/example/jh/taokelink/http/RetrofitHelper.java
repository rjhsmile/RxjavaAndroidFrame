package com.example.jh.taokelink.http;

import com.example.jh.taokelink.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/4/3.
 */

public class RetrofitHelper {

    private static int TIME = 10;
    private static RetrofitHelper retrofitHelper;

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

    public static <T> T createApi(Class<T> clazz) {
        return createApi(clazz, Constants.BASE_URL);
    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        //File cacheFile = new File(App.getInstance().getCacheDir(), "cache");
        //Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIME, TimeUnit.SECONDS)
                .readTimeout(TIME, TimeUnit.SECONDS)
                .connectTimeout(TIME, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)  //错误重连
                .addInterceptor(logging)//添加Log打印
                .addInterceptor(new ParamsInterceptor())//公共参数
                //.addInterceptor(new ReceivedCookiesInterceptor())
                //.cache(cache)//添加缓存
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //rx与retrofit混用
                .addConverterFactory(GsonConverterFactory.create())  //rx与Gson混用
                .build();

        return retrofit.create(clazz);
    }
}
