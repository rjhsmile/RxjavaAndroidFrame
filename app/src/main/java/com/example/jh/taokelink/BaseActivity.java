package com.example.jh.taokelink;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jh.taokelink.utils.nohttps.HttpListener;
import com.example.jh.taokelink.utils.nohttps.HttpResponseListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import butterknife.ButterKnife;

import static com.yanzhenjie.nohttp.rest.CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE;

/**
 * Created by jh on 2018/3/1.
 */

public abstract class BaseActivity extends AppCompatActivity {


    //用来标记取消。
    private Object object = new Object();
    //初始化请求队列，传入的参数是请求并发值。
    RequestQueue mQueue = NoHttp.newRequestQueue(1);
    protected abstract void initView();
    protected abstract int getLayoutResource();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        //初始化控件
        ButterKnife.bind(this);
        initView();
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

        mQueue.add(what, request, new HttpResponseListener<>(this, callback));
    }
}
