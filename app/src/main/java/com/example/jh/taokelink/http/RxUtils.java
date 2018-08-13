package com.example.jh.taokelink.http;

import java.util.concurrent.TimeUnit;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
 * RxUtils 关于 rx
 */
public class RxUtils {

    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).delay(0, TimeUnit.MILLISECONDS)
                        .unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
