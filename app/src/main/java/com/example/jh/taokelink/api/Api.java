package com.example.jh.taokelink.api;

import com.example.jh.taokelink.http.BaseResponse;
import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * 作者： Created by${raojianhui}
 * 日期：2018/8/13  11:29.
 * 项目：FrameDemo
 * 包名：com.example.jh.taokelink.api
 * 描述：api
 */

public interface Api {
    /**
     * 商家入驻
     *
     * @return
     */
    @POST("/api/system")
    Observable<BaseResponse<String>> getSalesSellerEntryList();

}
