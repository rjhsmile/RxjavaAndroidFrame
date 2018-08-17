package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.CopyBean;
import com.example.jh.taokelink.http.BaseArrayResponse;
import com.example.jh.taokelink.http.BaseResponse;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 作者： Created by${raojianhui}
 * 日期：2018/8/13  11:29.
 * 项目：FrameDemo
 * 包名：com.example.jh.taokelink.api
 * 描述：api
 */

public interface ApiService {
    /**
     * 系统初始化
     *
     * @param map
     * @return
     */
    @POST("/data/data.do")
    Observable<BaseArrayResponse<String>> getSalesSellerEntryList(
            @QueryMap Map<String, Object> map
    );

    /**
     * 口令查询接口
     *
     * @param map
     * @return
     */
    @POST("/api/search/paste_item")
    Observable<BaseResponse<CopyBean>> getCopyData(
            @QueryMap Map<String, Object> map
    );

}
