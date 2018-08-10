package com.example.jh.taokelink.net.request;


import android.content.Context;

import com.example.jh.taokelink.net.ResponseObserver;
import com.example.jh.taokelink.net.RetrofitHelper;
import com.example.jh.taokelink.net.SubScriberHandler;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * @author Veken
 * @date on 2017/11/15 18:01
 * @describe
 */

public class UserInfoRequest extends SubScriberHandler {

    private Context context;


    public void login() {

        Map<String, Object> map = new HashMap<>();
        //调用的接口方法，比如login方法
        map.put("method", "user.login");
        //加密和传一些常用参数
        handleFields(map);
        //申请网络
        Observable<String> observable = RetrofitHelper.getApiService().login(map);
        toSubscribe(observable, new ResponseObserver(context,true) {
            //数据返回在onNext
            @Override
            public void onSuccess(BaseRespond response) {
            }

            @Override
            public void onNext(@NonNull BaseRespond response) {
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

}
