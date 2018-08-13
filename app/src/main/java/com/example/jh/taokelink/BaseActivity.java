package com.example.jh.taokelink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Created by jh on 2018/3/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initView();
    protected abstract int getLayoutResource();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = getLayoutResource();
        if(layout!=0){
            setContentView(layout);
        }
        //初始化控件
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
