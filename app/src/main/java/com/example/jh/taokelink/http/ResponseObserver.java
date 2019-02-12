package com.example.jh.taokelink.http;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.http.exception.ApiException;
import com.example.jh.taokelink.http.exception.ErrorType;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.ToastUtils;
import com.example.jh.taokelink.widget.nicedialog.BaseNiceDialog;
import com.example.jh.taokelink.widget.nicedialog.NiceDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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

    /**
     * 成功抽象类
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    public abstract void onFail(int code, String message);

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
    public void onNext(T t) {
        //失败方法
        if (t instanceof BaseResponse || t instanceof BaseArrayResponse) {
            BaseResponse response = (BaseResponse) t;
            if (response.code != Keys.CODE_SUCCESS) {   //请求失败
                if (showErrorMsg)
                    ToastUtils.show(mContext.getString(R.string.service_connect_failed));
                    onComplete();
                return;
            }
        }

        //成功方法
        try {
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    /**
     * 完成回调
     */
    @Override
    public void onComplete() {
        if (mAutoDismiss) {
            dismiss();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    /**
     * 失败回调
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        onComplete();
        try {
            ApiException mException = ApiException.handleException(e);
            onFail(mException.code, mException.meg);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
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
