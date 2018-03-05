package com.example.jh.taokelink.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jh.taokelink.R;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 16:48
 * @PACKAGE_NAME：UiUtils
 */

public class UiUtils {

    /**
     * 饶建辉
     * 功能;防止多次点击提示
     */
    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }


    /**
     * 获取没有数据的空布局
     *
     * @param activity
     * @param rv
     * @return
     */
    public static View getEmptyView(Activity activity, RecyclerView rv) {
        if (activity == null) {
            return null;
        }
        View emptyView = activity.getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv.getParent(), false);
        return emptyView;
    }

    /**
     * 数据加载完成,获取没有更多数据的布局
     *
     * @param activity
     * @param sr
     * @return
     */
    public static View getFooterView(Activity activity, RecyclerView sr) {
        View view = activity.getLayoutInflater().inflate(R.layout.progress_end, (ViewGroup) sr.getParent(), false);
        return view;
    }

    /**
     * 设置状态栏背景状态
     */
    public static void StatusBarCompat(Activity activity, int color) {
        //当前手机版本为4.4及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            //透明状态栏
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);//状态栏背景颜色
        //tintManager.setStatusBarTintResource(R.mipmap.navbg);// 状态栏背景图片
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
