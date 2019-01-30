package com.example.jh.taokelink.http;

import android.text.TextUtils;
import android.util.Log;

import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.Md5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


/**
 * @author : rjhsmile
 * @create : 17/07/07.
 * 功能：拦截器组件（Interceptor）添加公共参数
 */
public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原先的请求
        Request originalRequest = chain.request();
        //重新构建url
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        //如果是post请求的话就把参数重新拼接一下，get请求的话就可以直接加入公共参数了
        if(originalRequest.method().equals("POST")){
            FormBody body = (FormBody) originalRequest.body();
            for(int i = 0; i < body.size();i++){
                Log.i("RequestFatory",body.name(i) + "---" + body.value(i));
                builder.addQueryParameter(body.name(i),body.value(i));
            }
        }

        //公共参数
        builder.addQueryParameter("appId", Constants.AppId);
        builder.addQueryParameter("platform", "1");
        builder.addQueryParameter("timestamp", String.valueOf(System.currentTimeMillis()));
        builder.addQueryParameter("version", "1");
        builder.addQueryParameter("sign", mSignParams(chain.request()));

        //新的url
        HttpUrl httpUrl = builder.build();
        Request request = originalRequest.newBuilder()
                .method(originalRequest.method(),originalRequest.body())
                .url(httpUrl).build();
        return chain.proceed(request);
    }

    private String mSignParams(Request request) throws UnsupportedEncodingException {
        FormBody formBody = (FormBody) request.body();

        Map<String, String> bodyMap = new HashMap<>();
        List<String> nameList = new ArrayList<>();

        for (int i = 0; i < formBody.size(); i++) {
            nameList.add(formBody.encodedName(i));
            bodyMap.put(formBody.encodedName(i), URLDecoder.decode(formBody.encodedValue(i), "UTF-8"));
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nameList.size(); i++) {
            builder.append(nameList.get(i)).append("=")
                    .append(URLDecoder.decode(bodyMap.get(nameList.get(i)), "UTF-8")).append("&");
        }
        builder.append("&api_token=").append(Constants.ApiToken);
        return Md5Util.md5(builder.toString());
    }
}