package com.example.jh.taokelink.http;

import com.example.jh.taokelink.BuildConfig;
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

    private static final int TIME = 10;
    private static OkHttpClient okHttpClient = null;

    public static <T> T createApi(Class<T> clazz) {
        return createApi(clazz, Constants.BASE_URL);
    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ReceivedCookiesInterceptor());
        builder.addInterceptor(new AddCookiesInterceptor());
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        // builder.addInterceptor(new LoggerInterceptor());
        //设置超时
        builder.connectTimeout(TIME, TimeUnit.SECONDS);
        builder.readTimeout(TIME, TimeUnit.SECONDS);
        builder.writeTimeout(TIME, TimeUnit.SECONDS);
        //错误重连
        //   builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }
}
