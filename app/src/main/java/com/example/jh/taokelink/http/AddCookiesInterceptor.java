package com.example.jh.taokelink.http;

import android.text.TextUtils;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : jc.lu
 * @create : 17/07/07.
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) App.getInstance().getSharedPreferences("config",
                App.getInstance().MODE_PRIVATE).getStringSet("cookie", null);                    //////////////////////////////////////////////////////////////////////////
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);////////////////////////////////////////////////////////////////////////////////////////////
            }
        }
        String language = new SPUtils(App.getInstance(),Constants.BASE_URL).getString(Constants.BASE_URL);
        if (TextUtils.isEmpty(language)) {
            language = Constants.BASE_URL;
        }
        builder.addHeader("Accept-Language", language);
        builder.addHeader("v", "1");
        return chain.proceed(builder.build());
    }
}