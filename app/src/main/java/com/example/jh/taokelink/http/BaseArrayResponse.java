package com.example.jh.taokelink.http;

import java.util.ArrayList;

/**
 * 列表数据基类
 *
 * @param <T>
 */
public class BaseArrayResponse<T> {
    public ArrayList<T> data;
    public int code;//			响应代码 1:查询成功
    public String message; //			查询成功 查询失败
    public boolean success;    //	是	True  false
}
