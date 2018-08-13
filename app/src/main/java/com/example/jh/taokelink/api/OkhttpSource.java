package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.SystemBean;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.RetrofitHelper;
import com.example.jh.taokelink.http.RxUtils;

import io.reactivex.Observable;


/**
 * 作者： Created by${raojianhui}
 * 日期：2018/8/13  11:29.
 * 项目：FrameDemo
 * 包名：com.example.jh.taokelink.api
 * 描述：
 */

public class OkhttpSource implements AppApi {

    private static volatile OkhttpSource instance = null;

    private AppApi getApi() {
        return RetrofitHelper.getInstance().createApi(AppApi.class);
    }

    public static OkhttpSource getInstance() {
        if (instance == null) {
            synchronized (OkhttpSource.class) {
                if (instance == null) {
                    instance = new OkhttpSource();
                }
            }
        }
        return instance;
    }


    @Override
    public Observable<BaseResponse<SystemBean>> getSalesSellerEntryList() {
        return getApi().getSalesSellerEntryList().compose(RxUtils.<BaseResponse<SystemBean>>rxSchedulerHelper());
    }

}
