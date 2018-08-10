package com.example.jh.taokelink.http;


import android.content.Context;
import com.example.jh.taokelink.App;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.http.exception.ApiException;
import com.example.jh.taokelink.http.exception.ErrorType;
import com.example.jh.taokelink.http.exception.ExceptionEngine;
import com.example.jh.taokelink.utils.NetworkUtils;

import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * 数据返回
 *
 * @param <T>
 */
public abstract class ResponseObserver<T> implements OnFinishListener, Observer<T> {
    private static final String TAG = "ResponseObserver";
    private Context mContext;
    private boolean mAutoDismiss = false;
    private boolean showErrorMsg = true;

    public ResponseObserver(Context context) {
        mContext = context;
    }


    public ResponseObserver(Context context, boolean autoDismiss) {
        mContext = context;
        this.mAutoDismiss = autoDismiss;
        if (autoDismiss) {

        }
    }

    public ResponseObserver(Context context, boolean autoDismiss, boolean showErrorMsg) {
        mContext = context;
        this.mAutoDismiss = autoDismiss;
        this.showErrorMsg = showErrorMsg;
        if (autoDismiss) {

        }
    }


    /**
     * 请求回调结束操作
     */
    @Override
    public void onFinish() {

    }

    @Override
    public void onFail(int code, String msg) {
        if (mAutoDismiss) {
            dismiss();
        }
    }

    public void dismiss() {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            int code = ((HttpException) e).code();
        }
        if (NetworkUtils.isNetworkAvailable()) {   //有网络
            ApiException exception = ExceptionEngine.handleException(e);
            switch (exception.code) {
                case 401:
                    clearLoginInfo();
                    break;
                case 410:
                    clearLoginInfo();
                    break;
                default:
                    showToast(showErrorMsg, mContext.getResources().getString(R.string.server_exception));
                    break;
//                case ErrorType.UNKONW:
//                    onFail(exception.code,exception.message);
//                    ToastUtil.showToast(App.getInstance(), exception.message);
//                    break;
//                case ErrorType.OTHER_NETWORK_ERROR:
//                    onFail(exception.code,exception.message);
//                    ToastUtil.showToast(App.getInstance(), exception.message);
//                    break;

            }
        } else {
            showToast(showErrorMsg, mContext.getResources().getString(R.string.connect_timeout));
            onFail(ErrorType.HTTP_ERROR, mContext.getResources().getString(R.string.connect_timeout));
        }
        e.printStackTrace();
        onFailure(ExceptionEngine.handleException(e));
        onFinish();
        if (mAutoDismiss) {
            dismiss();
        }
    }

    //提示语
    private void showToast(boolean show, String msg) {

    }

    private void clearLoginInfo() {

    }

    @Override
    public void onNext(final T t) {
        if (t instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) t;
            if (response.code != 1) {   //请求失败
                if (showErrorMsg)
                    onFail(response.code, response.message);
                return;
            }
        } else if (t instanceof BaseArrayResponse) {
            BaseArrayResponse response = (BaseArrayResponse) t;
            if (response.code != 1) {   //请求失败
                if (showErrorMsg)
                    //ToastUtil.showToast(mContext, response.message);
                    onFail(response.code, response.message);
                return;
            }
        }
        try {
            onSuccess(t);
            onFinish();
            if (mAutoDismiss) {
                dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }

    }

    public abstract void onSuccess(T t);

    public void onFailure(Throwable e) {
    }
}
