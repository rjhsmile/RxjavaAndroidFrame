package com.example.jh.taokelink.http;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * RxUtils 关于 rx
 * 操作符
 */
public class RxUtils {
    /**
     * 过滤操作符
     * throttleFirst（执行第一次事件）/ throttleLast（执行最后一次事件）
     * 防抖功能:指定时间内执行第一次时间
     */
    public static void click() {
        /**
         * 说明
         * 1. 此处采用了RxBinding：RxView.clicks(button) = 对控件点击进行监听，需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入Button控件，点击时，都会发送数据事件（但由于使用了throttleFirst（）操作符，所以只会发送该段时间内的第1次点击事件）
         */
       /* Button button;
        RxView.clicks(button)
                .throttleFirst(2, TimeUnit.SECONDS)  // 发送 2s内第1次点击按钮的事件
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });*/
    }

    /**
     * 过滤操作符
     * debounce（去抖动）
     * 联想搜索功能：发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据
     */
    public static void textChanges() {
        /**
         * 说明
         * 1. 此处采用了RxBinding：RxTextView.textChanges(name) = 对对控件数据变更进行监听（功能类似TextWatcher），需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入EditText控件，输入字符时都会发送数据事件（此处不会马上发送，因为使用了debounce（））
         * 3. 采用skip(1)原因：跳过 第1次请求 = 初始输入框的空字符状态
         **/
       /* EditText ed;
        RxTextView.textChanges(ed)
                .debounce(1, TimeUnit.SECONDS).skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {

                    }
                });*/
    }

    /**
     * 过滤操作符
     * skip（跳过）
     * 功能：跳过某个事件
     */
    public static void skip() {
        // 使用1：根据顺序跳过数据项
        Observable.just(1, 2, 3, 4, 5)
                .skip(1) // 跳过正序的前1项
                .skipLast(2) // 跳过正序的后2项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("", "获取到的整型事件元素是： " + integer);
                    }
                });
    }

    /**
     * 创建操作符：定时性，周期性
     * interval(间隔)
     * 功能：每隔指定时间 就发送 事件
     * <p>
     * 参数1 = 第1次延迟时间；
     * 参数2 = 间隔时间数字；
     * 参数3 = 时间单位；
     */
    public static void interval() {
        Observable.interval(1, 5, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e("", "accept: " + aLong);
            }
        });
    }

    /**
     * 创建操作符
     * timer(延时)
     * 功能：延迟指定时间后，发送1个数值0（Long类型）
     * <p>
     * 参数1：延时时间
     * 参数2 = 时间单位；
     */
    public static void timer() {
        Observable.timer(5, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        });
    }

    /**
     * 线程处理，异步和链式编程
     * 子线程执行接口，主线程回调数据显示
     * Schedulers 调度器
     * AndroidSchedulers.mainThread() 主线程
     * Schedulers.io()是子线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())  //子线程执行订阅关系
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());//回调在主线程
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
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper(final int delayTime) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> observable) {
                return observable
                        .delay(delayTime * 1000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
