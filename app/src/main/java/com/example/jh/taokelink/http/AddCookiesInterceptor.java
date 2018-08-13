package com.example.jh.taokelink.http;

import android.renderscript.Sampler;
import android.text.TextUtils;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.utils.AppUtils;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.Md5Util;
import com.example.jh.taokelink.utils.SPUtils;
import com.yanzhenjie.nohttp.tools.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
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
        builder.addHeader("appkey", Keys.appkey);
        builder.addHeader("os", "android");
        builder.addHeader("t", System.currentTimeMillis() + "");//时间戳
        builder.addHeader("v", AppUtils.getVersionName(App.getInstance()));//app版本号
        //签名
        builder.addHeader("sign", singnParam(chain));//md5签名串
        return chain.proceed(builder.build());
    }

    /**
     * 获取字符串+签名
     *
     * @param <T>
     * @param chain
     * @return
     */
    private <T> String singnParam(Chain chain) {
        Request request = chain.request();
        String url = request.url().toString();
        //获取参数列表
        String[] parts = url.split("\\?");
        //TreeMap里面的数据会按照key值自动升序排列
        TreeMap<String, String> param_map = new TreeMap<String, String>();
        //获取参数对
        String[] param_pairs = parts[1].split("&");
        for (String pair : param_pairs) {
            String[] param = pair.split("=");
            if (param.length != 2) {
                //没有value的参数不进行处理
                continue;
            }
            param_map.put(param[0], param[1]);
        }
        StringBuilder param = new StringBuilder();
        Iterator it = param_map.keySet().iterator();
        //拼接参数
        while (it.hasNext()) {
            String key = it.next().toString();
            String value = param_map.get(key).toString();
            param.append(key + value);
        }
        param.append(Keys.appsecret);
        return Md5Util.md5(param.toString());
    }

}