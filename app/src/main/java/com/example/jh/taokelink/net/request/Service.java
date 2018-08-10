package com.example.jh.taokelink.net.request;


import com.example.jh.taokelink.Constants;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * @author Veken
 */
public interface Service {

    /**
     * 登录的service
     * @param fields
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.BASE_URL)
    Observable<String> login(@FieldMap Map<String, Object> fields);

}
