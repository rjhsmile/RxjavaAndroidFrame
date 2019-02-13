package com.example.jh.taokelink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jh on 2018/3/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    public String TAG = this.getClass().getName();

    public CompositeDisposable mCompositeDisposable;

    protected abstract int getLayoutResource();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = getLayoutResource();
        if (layout != 0) {
            setContentView(layout);
        }
        mCompositeDisposable = new CompositeDisposable();//Rxjava订阅初始化
        //初始化控件
        ButterKnife.bind(this);
        initView();
        initData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }
}
