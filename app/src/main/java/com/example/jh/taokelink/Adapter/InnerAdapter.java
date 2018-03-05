package com.example.jh.taokelink.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.jh.taokelink.view.activity.GuideActivity;

//引导页InnerAdapter对象
public class InnerAdapter extends PagerAdapter {
    private GuideActivity guideActivity;
    private int[] imgs;

    public InnerAdapter(GuideActivity guideActivity, int[] imgs) {
        this.guideActivity = guideActivity;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 1.获得item vie
        ImageView iv = new ImageView(guideActivity);
        iv.setScaleType(ScaleType.FIT_XY);
        // 2.获得item data
        int imaId = imgs[position];
        // 3.将item data加入item view中
        iv.setImageResource(imaId);
        // 4.将item添加到容器
        container.addView(iv);
        return iv;
    }
}


