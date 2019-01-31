package com.example.jh.taokelink.http;

/**
 * Created by dqm on 2018/3/26.
 * 基本数据基类
 */

public class BaseResponse<T> {
    public int code;//			响应代码 1:查询成功
    public String msg; //			查询成功 查询失败
    public T data;
}
