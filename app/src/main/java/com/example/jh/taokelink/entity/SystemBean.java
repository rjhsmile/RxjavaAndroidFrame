package com.example.jh.taokelink.entity;

/**
 * Created by lenovo on 2018/6/1.
 */

public class SystemBean {

    /**
     * code : 200
     * message : null
     * data : {"appkey":1000001,"pid":"mm_128824398_41290652_255150354","tbAppkey":null,"tbAppsecret":null,"getuiAppid":null,"getuiAppkey":null,"getuiAppsecret":null,"feedbackUrl":null,"aboutUsUrl":"http://api.mapprouter.com/h5/about.html","couponGuideUrl":"http://api.mapprouter.com/h5/coupon_guide.html"}
     */

    private int code;
    private Object message;
    private DataEntity data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * appkey : 1000001
         * pid : mm_128824398_41290652_255150354
         * tbAppkey : null
         * tbAppsecret : null
         * getuiAppid : null
         * getuiAppkey : null
         * getuiAppsecret : null
         * feedbackUrl : null
         * aboutUsUrl : http://api.mapprouter.com/h5/about.html
         * couponGuideUrl : http://api.mapprouter.com/h5/coupon_guide.html
         */

        private int appkey;
        private String pid;
        private Object tbAppkey;
        private Object tbAppsecret;
        private Object getuiAppid;
        private Object getuiAppkey;
        private Object getuiAppsecret;
        private Object feedbackUrl;
        private String aboutUsUrl;
        private String couponGuideUrl;

        public int getAppkey() {
            return appkey;
        }

        public void setAppkey(int appkey) {
            this.appkey = appkey;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public Object getTbAppkey() {
            return tbAppkey;
        }

        public void setTbAppkey(Object tbAppkey) {
            this.tbAppkey = tbAppkey;
        }

        public Object getTbAppsecret() {
            return tbAppsecret;
        }

        public void setTbAppsecret(Object tbAppsecret) {
            this.tbAppsecret = tbAppsecret;
        }

        public Object getGetuiAppid() {
            return getuiAppid;
        }

        public void setGetuiAppid(Object getuiAppid) {
            this.getuiAppid = getuiAppid;
        }

        public Object getGetuiAppkey() {
            return getuiAppkey;
        }

        public void setGetuiAppkey(Object getuiAppkey) {
            this.getuiAppkey = getuiAppkey;
        }

        public Object getGetuiAppsecret() {
            return getuiAppsecret;
        }

        public void setGetuiAppsecret(Object getuiAppsecret) {
            this.getuiAppsecret = getuiAppsecret;
        }

        public Object getFeedbackUrl() {
            return feedbackUrl;
        }

        public void setFeedbackUrl(Object feedbackUrl) {
            this.feedbackUrl = feedbackUrl;
        }

        public String getAboutUsUrl() {
            return aboutUsUrl;
        }

        public void setAboutUsUrl(String aboutUsUrl) {
            this.aboutUsUrl = aboutUsUrl;
        }

        public String getCouponGuideUrl() {
            return couponGuideUrl;
        }

        public void setCouponGuideUrl(String couponGuideUrl) {
            this.couponGuideUrl = couponGuideUrl;
        }
    }
}
