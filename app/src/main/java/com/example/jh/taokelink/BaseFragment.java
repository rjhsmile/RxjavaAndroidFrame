package com.example.jh.taokelink;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018-03-04.
 */

public abstract class BaseFragment extends Fragment {
    private Activity mActivity;
    private View mCacheView;
    private Unbinder unbinder;

    protected abstract int getLayoutResource();


    protected abstract void initView();

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
        unbinder= ButterKnife.bind(this, mCacheView);

        initView();

        return mCacheView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
