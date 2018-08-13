package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.RiderBean;
import com.example.jh.taokelink.http.BaseResponse;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
     * 商家入驻
     *
     * @return
     */
    @POST("openService/deliveryRecruit")
    Observable<BaseResponse<RiderBean>> getSalesSellerEntryList(
            @Query("clientType") String clientType,
            @Query("globalCode") String globalCode,
            @Query("mobile") String mobile,
            @Query("mCode") String mCode
    );

}
