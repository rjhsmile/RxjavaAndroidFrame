package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.Categorys;
import com.example.jh.taokelink.entity.SystemBean;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.RetrofitHelper;
import com.example.jh.taokelink.http.RxUtils;

import java.util.Map;

import io.reactivex.Observable;


/**
 * 作者： Created by${raojianhui}
 * 日期：2018/8/13  11:29.
 * 项目：FrameDemo
 * 包名：com.example.jh.taokelink.api
 * 描述：
 */

public class ApiSource implements ApiService {

    private static volatile ApiSource instance = null;

    private ApiService getApi() {
        return RetrofitHelper.getInstance().createApi(ApiService.class);
    }

    private ApiService getApi(String baseUrl) {
        return RetrofitHelper.getInstance().createApi(ApiService.class, baseUrl);
    }

    public static ApiSource getInstance() {
        if (instance == null) {
            synchronized (ApiSource.class) {
                if (instance == null) {
                    instance = new ApiSource();
                }
            }
        }
        return instance;
    }

    /**
     * 系统初始化
     *
     * @return
     */

    @Override
    public Observable<BaseResponse<SystemBean>> getSystem(String appId) {
        return getApi().getSystem(appId).compose(RxUtils.<BaseResponse<SystemBean>>rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResponse<Categorys>> getSalesSellerEntryList(int start, int count) {
        return getApi().getSalesSellerEntryList(start, count).
                compose(RxUtils.<BaseResponse<Categorys>>rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResponse<String>> getCopyData(int page) {
        return getApi().getCopyData(page).compose(RxUtils.<BaseResponse<String>>rxSchedulerHelper());
    }
}
