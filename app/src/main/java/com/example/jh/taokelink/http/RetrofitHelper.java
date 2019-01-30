package com.example.jh.taokelink.http;
import com.example.jh.taokelink.BuildConfig;
import com.example.jh.taokelink.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/4/3.
 */

public class RetrofitHelper {

    private static int TIME = 10;
    private static RetrofitHelper retrofitHelper;
    private static OkHttpClient okHttpClient = null;

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

       /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(App.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //builder.addInterceptor(new ParamsInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        //设置超时
        builder.connectTimeout(TIME, TimeUnit.SECONDS);
        builder.readTimeout(TIME, TimeUnit.SECONDS);
        builder.writeTimeout(TIME, TimeUnit.SECONDS);
        //设置缓存
        //builder.cache(cache);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)//设置OkHttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())  //rx与Gson混用
                .build();

        return retrofit.create(clazz);
    }
}
