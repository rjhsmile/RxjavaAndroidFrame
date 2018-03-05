package com.example.jh.taokelink.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.utils.ApiContents;
import com.example.jh.taokelink.utils.nohttps.HttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 17:11
 * @PACKAGE_NAME：MainFragment
 */

public class MainFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建请求对象。。
        Request<String> request = NoHttp.createStringRequest("http://wakeup.s1.natapp.cc"+"/api/search", RequestMethod.POST);
        if (request != null) {
//            request.add("type", "hot");//哪个种类的数据
//            request.add("p", "7");
//            request.add("token", "caa65cb4c173f4f661406cef515acc36");
            request(ApiContents.WHAT_HOME_GOODS, request, listener);
        }
    }


    HttpListener<String> listener=new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {
            String result = response.get();
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }

        @Override
        public void onFinish(int what) {

        }

        @Override
        public void onStart(int what) {

        }
    };
}
