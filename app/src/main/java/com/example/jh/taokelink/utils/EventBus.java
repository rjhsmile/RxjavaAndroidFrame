package com.example.jh.taokelink.utils;

/**
 * Created by lenovo on 2018/6/26.
 */

public class EventBus {
    public int type;
    public String mStr;

    public EventBus(int type) {
        this.type = type;
    }

    public EventBus(int type, String mStr) {
        this.type = type;
        this.mStr = mStr;
    }

    public int getType() {
        return type;
    }
}
