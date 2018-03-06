package com.example.jh.taokelink;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jh.taokelink.utils.nohttps.HttpListener;
import com.example.jh.taokelink.utils.nohttps.HttpResponseListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;


import butterknife.ButterKnife;

import static com.yanzhenjie.nohttp.rest.CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE;

/**
 * Created by Administrator on 2018-03-04.
 */

public abstract class BaseFragment extends Fragment {
    //用来标记取消。
    private Object object = new Object();
    //初始化请求队列，传入的参数是请求并发值。
    RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Activity mActivity;
    private View mCacheView;

    protected abstract void initView();

    protected abstract int getLayoutResource();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity=getActivity();
    }

    /**
     * 页面开始创建
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mCacheView == null) {
            mCacheView = inflater.inflate(getLayoutResource(), null);
        }
        ViewGroup parent = (ViewGroup) mCacheView.getParent();
        if (parent != null) {
            parent.removeView(mCacheView);
        }
        ButterKnife.bind(this, mCacheView);

        initView();

        return mCacheView;
    }

    /**
     * 发起请求。
     *
     * @param what     what.
     * @param request  请求对象。
     * @param callback 回调函数。
     */
    public <T> void request(int what, Request<T> request, HttpListener<T> callback) {
        request.setCancelSign(object);
        request.setTag(object);

        //缓存配置
        request.setCacheKey("" + what);
        //先获取网络数据，网络请求失败读取缓存
        request.setCacheMode(REQUEST_NETWORK_FAILED_READ_CACHE);

        mQueue.add(what, request, new HttpResponseListener<>(mActivity, callback));
    }
}
