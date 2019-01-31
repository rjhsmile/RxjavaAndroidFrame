package com.example.jh.taokelink.http;

import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.Md5Util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : rjhsmile
 * @create : 17/07/07.
 * 功能：拦截器组件（Interceptor）添加公共参数
 */
public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("GET")) {
            request = addGetParams(request);
        } else if (request.method().equals("POST")) {
            request = addPostParams(request);
        }
        return chain.proceed(request);
    }


    //get请求 添加公共参数 签名
    private static Request addGetParams(Request request) {
        //添加公共参数
        HttpUrl httpUrl = request.url()
                .newBuilder()
                .addQueryParameter("appId", Constants.AppId)
                .addQueryParameter("platform", "1")
                .addQueryParameter("timestamp", String.valueOf(System.currentTimeMillis()))
                .addQueryParameter("version", "1")
                .build();

        //添加签名
        Set<String> nameSet = httpUrl.queryParameterNames();
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(nameSet);
        Collections.sort(nameList);

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < nameList.size(); i++) {
            buffer.append(nameList.get(i)).append("=").append(httpUrl.queryParameterValues(nameList.get(i)) != null &&
                    httpUrl.queryParameterValues(nameList.get(i)).size() > 0 ? httpUrl.queryParameterValues(nameList.get(i)).get(0) : "");
        }

        buffer.append("&api_token").append("=").append(Constants.ApiToken);
        httpUrl = httpUrl.newBuilder()
                .addQueryParameter("sign", Md5Util.md5(buffer.toString()))
                .build();
        request = request.newBuilder().url(httpUrl).build();
        return request;
    }

    //post 添加签名和公共参数
    private Request addPostParams(Request request) throws UnsupportedEncodingException {
        if (request.body() instanceof FormBody) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            FormBody formBody = (FormBody) request.body();

            //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
            for (int i = 0; i < formBody.size(); i++) {
                bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
            }

            formBody = bodyBuilder
                    .addEncoded("appId", Constants.AppId)
                    .addEncoded("platform", "1")
                    .addEncoded("timestamp", String.valueOf(System.currentTimeMillis()))
                    .addEncoded("version", "1")
                    .build();

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
            builder.append("api_token").append("=").append(Constants.ApiToken);
            formBody = bodyBuilder.addEncoded("sign", Md5Util.md5(builder.toString())).build();
            request = request.newBuilder().post(formBody).build();
        }
        return request;
    }
}