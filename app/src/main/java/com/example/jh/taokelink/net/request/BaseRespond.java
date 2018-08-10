package com.example.jh.taokelink.net.request;

/**
 * Created by suxi on 2017/5/27.
 */

public class BaseRespond {
    /**
     * resCode : 400
     * resDesc : 用户名username注册失败，用户名已存在
     */

    private String resCode;
    private String resDesc;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }
}
