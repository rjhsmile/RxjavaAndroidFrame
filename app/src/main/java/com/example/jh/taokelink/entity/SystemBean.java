package com.example.jh.taokelink.entity;

/**
 * Created by lenovo on 2018/6/1.
 */

public class SystemBean {

    /**
     * code : 10000
     * msg : success
     * data : {"pid":"mm_48512871_9268566_28738750165","about":"http://coupon.jumtuan.com/about?appId=4a93ec5ce6e911e8971a00163e100116"}
     */

    private int code;
    private String msg;
    private DataEntity data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * pid : mm_48512871_9268566_28738750165
         * about : http://coupon.jumtuan.com/about?appId=4a93ec5ce6e911e8971a00163e100116
         */

        private String pid;
        private String about;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }
    }
}
