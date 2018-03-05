package com.example.jh.taokelink.utils.nohttps;

import com.alibaba.fastjson.JSON;


public class JsonUtils {

    public static <T> T parseObject(String text, Class<T> clazz) {
        try {
            T t = JSON.parseObject(text, clazz);
            return t;
        } catch (Exception e) {
            return null;
        }
    }
}
