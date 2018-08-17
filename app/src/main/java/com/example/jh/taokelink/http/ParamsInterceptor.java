package com.example.jh.taokelink.http;

import android.util.Log;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.utils.AppUtils;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.Md5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
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
        Request oldRequest = chain.request();
        // 新的请求
        Request.Builder builder = oldRequest.newBuilder();
        builder.method(oldRequest.method(), oldRequest.body());
        //添加公共参数,添加到header中
        builder.header("appkey", Keys.appkey);
        builder.header("os", "android");
        builder.header("t", String.valueOf(System.currentTimeMillis()));//时间戳
        builder.header("v", "1.0");//app版本号
        builder.header("sign", singnParam(oldRequest));

        Request newRequest = builder.build();

        return chain.proceed(newRequest);

        //Request request = chain.request();
        /* //请求定制：添加请求头
        Request.Builder builder = request.newBuilder();
        builder.header("appkey", Keys.appkey);
        builder.header("os", "android");
        builder.header("t",  String.valueOf(System.currentTimeMillis()));//时间戳
        builder.header("v", "1.0");//app版本号
        builder.header("sign", singnParam(request));
        return chain.proceed(builder.build());*/

        /*HttpUrl url = request.url().newBuilder() //请求尾部链接
                .addQueryParameter("appkey", Keys.appkey)
                .addQueryParameter("os", "android")
                .addQueryParameter("t", System.currentTimeMillis() + "")
                .addQueryParameter("v", "1.0")
                .addQueryParameter("sign", singnParam(request))
                .build();

         request = request.newBuilder()
                .method(request.method(), request.body())
                //添加到请求里
                .url(url)
                .build();

        return chain.proceed(request);*/
    }

    /**
     * 获取字符串+签名
     *
     * @param request
     * @param <T>
     * @return
     */
    private <T> String singnParam(Request request) {
        HttpUrl url = request.url();
        String scheme = url.scheme();//  http https
        String host = url.host();//   127.0.0.1
        String path = url.encodedPath();//  /test/upload/img
        String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345
        //创建StringBuffer准备拼接参数
        StringBuffer sb = new StringBuffer();
        //sb.append(scheme).append(host).append(path).append("?");
        Set<String> queryList = url.queryParameterNames();
        Iterator<String> iterator = queryList.iterator();
        //参数拼接
        for (int i = 0; i < queryList.size(); i++) {
            String queryName = iterator.next();
            String queryKey = url.queryParameter(queryName);
            sb.append(queryName).append(queryKey);
           /* if (iterator.hasNext()) {
                sb.append("&");
            }*/
        }
        sb.append("appkey").append(Keys.appkey).append("os").append("android")
                .append("t").append(String.valueOf(System.currentTimeMillis()))
                .append("V").append("1.0");
        //ParameterNames和ParameterKey拼接进行加密
        String newUrl = Md5Util.md5(sb + Keys.appsecret);
        return newUrl;
    }

}