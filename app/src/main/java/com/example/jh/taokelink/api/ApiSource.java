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
     * @param equipment
     * @return
     */

    @Override
    public Observable<BaseResponse<SystemBean>> getSystem(String equipment) {
        return getApi().getSystem(equipment).compose(RxUtils.<BaseResponse<SystemBean>>rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResponse<String>> getCopyData(int page) {
        return getApi().getCopyData(page).compose(RxUtils.<BaseResponse<String>>rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResponse<String>> videoLogin(String account, String password) {
        return getApi().videoLogin(account, password).compose(RxUtils.<BaseResponse<String>>rxSchedulerHelper());
    }
}
