package com.example.jh.taokelink.http;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author : jc.lu
 * @create : 17/07/07.
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                //响应头为 PHPSESSID=9r4vbmvditj1rq4c7gunnu2c42; path=/
                //去除path=/
                header = header.substring(0, header.indexOf(";") + 1);

                Log.i("cook", header);
                if (header.contains("tokenKey")) {
                    cookies.add(header);
                    SharedPreferences.Editor config = App.getInstance()
                            .getSharedPreferences("config", App.getInstance().MODE_PRIVATE).edit();
                    config.putStringSet("cookie", cookies);
                    config.commit();
                }
            }
        }

        return originalResponse;
    }
}
