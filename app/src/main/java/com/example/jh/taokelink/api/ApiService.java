package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.Categorys;
import com.example.jh.taokelink.entity.CopyBean;
import com.example.jh.taokelink.http.BaseArrayResponse;
import com.example.jh.taokelink.http.BaseResponse;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
     * @return
     */
    @FormUrlEncoded
    @POST("v2/movie/top250")
    Observable<BaseResponse<Categorys>> getSalesSellerEntryList(
            @Field("start") int start , @Field("count") int count
    );

    /**
     * 口令查询接口
     *
     * @param map
     * @return
     */
    @POST("api/search/paste_item")
    Observable<BaseResponse<CopyBean>> getCopyData(
            @QueryMap Map<String, Object> map
    );

}
