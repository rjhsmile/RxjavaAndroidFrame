package com.example.jh.taokelink.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.example.jh.taokelink.BaseActivity;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.SPUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void initView() {
        //全屏
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity();
            }
        }, 1000);
    }

    private void startNextActivity() {
        //1.读取状态信息？
        SPUtils sp = new SPUtils(this);
        boolean isUsed=sp.getBoolean("isUsed",false);
        //2.根据使用状态，启动下个页面
        if(isUsed){//表示使用过
            Log.i("TAG", "进入系统主页");
            startActivity(new Intent(this,MainActivity.class));
        }else{//没有使用过
            Log.i("TAG", "进入新手指导页");
            startActivity(new Intent(this, GuideActivity.class));
            sp.putBoolean("isUsed", true);
        }
        //3.关闭当前页面
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
