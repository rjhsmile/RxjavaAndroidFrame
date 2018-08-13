package com.example.jh.taokelink.http;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.jh.taokelink.R;
import com.example.jh.taokelink.http.exception.ExceptionEngine;
import com.example.jh.taokelink.widget.nicedialog.BaseNiceDialog;
import com.example.jh.taokelink.widget.nicedialog.NiceDialog;

import io.reactivex.disposables.Disposable;
import rx.Observer;


/**
 * 数据返回
 *
 * @param <T>
 */
public abstract class ResponseObserver<T> implements Observer<T> {
    private static final String TAG = "ResponseObserver";
    private Context mContext;
    private boolean mAutoDismiss = false;
    private boolean showErrorMsg = true;
    private BaseNiceDialog progressBar;
    private Disposable mDisposable;//接口取消

    /**
     * 成功抽象类
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    public ResponseObserver(Context context) {
        mContext = context;
    }


    public ResponseObserver(Context context, boolean autoDismiss) {
        mContext = context;
        this.mAutoDismiss = autoDismiss;
        if (autoDismiss) {
            showProgressBar();
        }
    }

    public ResponseObserver(Context context, boolean autoDismiss, boolean showErrorMsg) {
        mContext = context;
        this.mAutoDismiss = autoDismiss;
        this.showErrorMsg = showErrorMsg;
        if (autoDismiss) {
            showProgressBar();
        }
    }


    /**
     * 成功方法回调
     *
     * @param t
     */
    @Override
    public void onNext(final T t) {
        //失败方法
        if (t instanceof BaseResponse || t instanceof BaseArrayResponse) {
            BaseResponse response = (BaseResponse) t;
            if (response.code != 1) {   //请求失败
                if (showErrorMsg)
                    //onFail(response.code, response.message);
                    onCompleted();
                return;
            }
        }

        //成功方法
        try {
            onSuccess(t);
            onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    /**
     * 完成回调
     */
    @Override
    public void onCompleted() {
        if (mAutoDismiss) {
            dismiss();
        }
    }

    /**
     * 失败回调
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        onCompleted();
        e.printStackTrace();
        ExceptionEngine.handleException(e);
    }

    /**
     * 加载框
     */
    public void showProgressBar() {
        AppCompatActivity mActivity = (AppCompatActivity) mContext;
        //初始化加载框
        progressBar = NiceDialog.init()
                .setLayoutId(R.layout.dialog_progress)
                .setWidth(100)
                .setHeight(100)
                .setDimAmount(0.1f)
                .setOutCancel(true)
                .show(mActivity.getSupportFragmentManager());
    }


    /**
     * 加载框消失
     */
    public void dismiss() {
        if (null != progressBar) {
            progressBar.dismiss();
        }
    }
}
