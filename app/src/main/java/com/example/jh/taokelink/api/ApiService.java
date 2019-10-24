package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.SystemBean;
import com.example.jh.taokelink.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
     * @param equipment
     * @return
     */
    @FormUrlEncoded
    @POST("init")
    Observable<BaseResponse<SystemBean>> getSystem(
            @Field("equipment") String equipment
    );

    /**
     * 查询接口
     *
     * @return
     */
    @POST("/api/recommend")
    Observable<BaseResponse<String>> getCopyData(
            @Field("page") int page
    );


    /**
     * 查询接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/StandardApiAction_login.action")
    Observable<BaseResponse<String>> videoLogin(
            @Field("account") String account,
            @Field("password") String password
    );

}
