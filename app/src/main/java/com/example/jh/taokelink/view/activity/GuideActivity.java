package com.example.jh.taokelink.view.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jh.taokelink.Adapter.InnerAdapter;
import com.example.jh.taokelink.BaseActivity;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.widget.SplashView;
import butterknife.BindView;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.btn_down)
    Button mBtnDown;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.point_group)
    LinearLayout mPointGroup;
    @BindView(R.id.red_point)
    ImageView mRedPoint;

    int[] imgs = {R.mipmap.guide01, R.mipmap.guide01, R.mipmap.guide01};
    private int mPointMargin;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        startGuideActivity();
        /*//1.读取状态信息？
        SPUtils spUtils = new SPUtils(this, "shared_preferences");
        boolean isUsed = spUtils.getBoolean("isUsed", false);
        //2.根据使用状态，启动下个页面
        if (isUsed) {//表示使用过
            Log.i("TAG", "进入系统主页");
            initSplashView();
        } else {//没有使用过
            Log.i("TAG", "进入新手指导页当前页");
            //引导页
            startGuideActivity();
            spUtils.putBoolean("isUsed", true);
        }*/
    }

    @Override
    protected void initData() {

    }

    /**
     * 引导页
     */
    private void startGuideActivity() {
        //1、创建三个点
        setPagerIndicator();
        // 2.构建adapter
        PagerAdapter adapter = new InnerAdapter(this, imgs);
        // 3.关联adapter
        mViewPager.setAdapter(adapter);
        // 4.设置监听器
        mViewPager.setOnPageChangeListener(this);

        // 获取视图树对象，通过监听白点布局的显示，然后获取两个圆点之间的距离
        mRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 此时layout布局已经显示出来了，可以获取小圆点之间的距离了
                mPointMargin = mPointGroup.getChildAt(1).getLeft() - mPointGroup.getChildAt(0).getLeft();
                // 将自己移除掉
                mRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


    /**
     * 欢迎页本地和网路图片
     */
    private void initSplashView() {
        // call after setContentView(R.layout.activity_sample);
        SplashView.showSplashView(this, 3, R.mipmap.guide01, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {
                Log.d("SplashView", "img clicked. actionUrl: " + actionUrl);
                Toast.makeText(GuideActivity.this, "img clicked.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                Log.d("SplashView", "dismissed, initiativeDismiss: " + initiativeDismiss);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        // call this method anywhere to update splash view data
        SplashView.updateSplashData(this, "http://ww2.sinaimg.cn/large/72f96cbagw1f5mxjtl6htj20g00sg0vn.jpg", "http://jkyeo.com");
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 页面滑动的时候，动态的获取小圆点的左边距
        int leftMargin = (int) (mPointMargin * (position + positionOffset));
        // Log.d("GuideActivity", "leftMargin:" + leftMargin);

        // 获取布局参数，然后设置布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRedPoint.getLayoutParams();
        // 修改参数
        params.leftMargin = leftMargin;
        // 重新设置布局参数
        mRedPoint.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        // 最后一页
        if (position == imgs.length - 1) {
            mBtnDown.setVisibility(View.VISIBLE);
        } else {
            // 不是最后一页
            mBtnDown.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //创建三个点
    private void setPagerIndicator() {
        for (int i = 0; i < imgs.length; i++) {
            // 设置底部小圆点
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.point_bg_normal);

            int pointSize = getResources().getDimensionPixelSize(R.dimen.point_size);
            // 设置白色点的布局参数
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(pointSize, pointSize);
            if (i > 0) {
                params1.leftMargin = getResources().getDimensionPixelSize(R.dimen.point_margin);
            }
            point.setLayoutParams(params1);

            // 设置红点的布局参数
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(pointSize, pointSize);
            mRedPoint.setLayoutParams(params2);

            // 灰点添加到容器
            mPointGroup.addView(point);
        }
    }

    @OnClick({R.id.btn_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_down:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
