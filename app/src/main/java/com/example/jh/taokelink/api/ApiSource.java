package com.example.jh.taokelink.api;

import com.example.jh.taokelink.entity.Categorys;
import com.example.jh.taokelink.entity.CopyBean;
import com.example.jh.taokelink.http.BaseArrayResponse;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.RetrofitHelper;
import com.example.jh.taokelink.http.RxUtils;

import java.util.Map;

import retrofit2.http.Field;
import rx.Observable;


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


    @Override
    public Observable<BaseResponse<Categorys>> getSalesSellerEntryList(int start, int count) {
        return getApi().getSalesSellerEntryList(start, count).
                compose(RxUtils.<BaseResponse<Categorys>>rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResponse<CopyBean>> getCopyData(Map<String, Object> map) {
        return getApi().getCopyData(map).compose(RxUtils.<BaseResponse<CopyBean>>rxSchedulerHelper());
    }
}
