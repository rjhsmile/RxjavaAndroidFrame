package com.example.jh.taokelink.http;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * RxUtils 关于 rx
 */
public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()) //网络请求是在子线程的
                        .observeOn(AndroidSchedulers.mainThread());//界面更新在主线程
            }
        };
    }

    /**
     * 统一线程处理
     * 请求时间延迟
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper(final int delaySeconds) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .delay(delaySeconds * 1000, TimeUnit.MICROSECONDS)//表示延时多少秒后执行
                        .subscribeOn(Schedulers.io())//网络请求是在子线程的
                        .observeOn(AndroidSchedulers.mainThread());//界面更新在主线程
            }
        };
    }

}
