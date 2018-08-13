package com.example.jh.taokelink.http;

/**
 * Created by dqm on 2018/3/6.
 */

public interface OnFinishListener {

    void onFinish();

    void onFail(int code, String msg);   //用于区分网络还是数据请求  0:没有网络  1：请求成功返回数据失败  2：服务器异常了


}
