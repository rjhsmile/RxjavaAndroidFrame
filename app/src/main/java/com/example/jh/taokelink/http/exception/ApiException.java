package com.example.jh.taokelink.http.exception;


public class ApiException extends RuntimeException {


    public int code;
    public String message;

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
