package com.example.jh.taokelink.net;

import android.text.TextUtils;
import com.example.jh.taokelink.App;
import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.utils.SHA1;
import com.example.jh.taokelink.utils.SPUtils;
import java.security.DigestException;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Veken
 * @date on 2017/11/15 18:01
 * @describe
 */

public class SubScriberHandler {

    /**
     * @param o
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> o, ResponseObserver observer) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 添加共同参数 SHA1加密
     *
     * @param fields
     */
    public void handleFields(Map<String, Object> fields) {
        fields.put("appKey", "00001");
        fields.put("v", "1.0");
        String sessionId = new SPUtils(App.getInstance(), "sharedpreferences").getString(Constants.SESSIONID_STRING);
        if (!TextUtils.isEmpty(sessionId))
            fields.put(Constants.SESSIONID_STRING, sessionId);
        String sha1 = null;
        try {
            sha1 = SHA1.SHA1(fields);
        } catch (DigestException e) {
            e.printStackTrace();
        }
        fields.put("sign", sha1);
    }
}
