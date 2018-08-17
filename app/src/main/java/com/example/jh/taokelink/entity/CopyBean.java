package com.example.jh.taokelink.entity;

/**
 * Created by Administrator on 2018-06-03.
 */

public class CopyBean {

    /**
     * code : 200
     * message : null
     * data : {"itemId":564127231858,"pictUrl":"http://gaitaobao4.alicdn.com/tfscom/i4/1667141684/TB14XIVfwjN8KJjSZFkXXaboXXa_!!0-item_pic.jpg","title":"Daphne/达芙妮春季甜美蝴蝶结平底羊皮舒适单鞋女简约时尚风","userType":1,"price":"59.00","couponPrice":"15","afterCouponPrice":"44.00","sales":"14件","ulandUrl":"http://uland.taobao.com/coupon/edetail?e=w71GJQAAPPMGQASttHIRqYWd14voi5bAD6hRu8l6%2FAtYomNqLX%2FiF9SjZh5bxSCzOdlBBUYYapcBn7YldVZ6wrLUn2MBBRxwbd76m3V5xpbWuXRUzA7XX47xPgObpwdfkdRZWpI9SpXZcb3bnsh8GiUzVkkdwsIm&amp;engpvid=100_10.103.66.139_6876_9701528014315583683"}
     */

    private int code;
    private Object message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * itemId : 564127231858
         * pictUrl : http://gaitaobao4.alicdn.com/tfscom/i4/1667141684/TB14XIVfwjN8KJjSZFkXXaboXXa_!!0-item_pic.jpg
         * title : Daphne/达芙妮春季甜美蝴蝶结平底羊皮舒适单鞋女简约时尚风
         * userType : 1
         * price : 59.00
         * couponPrice : 15
         * afterCouponPrice : 44.00
         * sales : 14件
         * ulandUrl : http://uland.taobao.com/coupon/edetail?e=w71GJQAAPPMGQASttHIRqYWd14voi5bAD6hRu8l6%2FAtYomNqLX%2FiF9SjZh5bxSCzOdlBBUYYapcBn7YldVZ6wrLUn2MBBRxwbd76m3V5xpbWuXRUzA7XX47xPgObpwdfkdRZWpI9SpXZcb3bnsh8GiUzVkkdwsIm&amp;engpvid=100_10.103.66.139_6876_9701528014315583683
         */

        private long itemId;
        private String pictUrl;
        private String title;
        private int userType;
        private String price;
        private String couponPrice;
        private String afterCouponPrice;
        private String sales;
        private String ulandUrl;

        public long getItemId() {
            return itemId;
        }

        public void setItemId(long itemId) {
            this.itemId = itemId;
        }

        public String getPictUrl() {
            return pictUrl;
        }

        public void setPictUrl(String pictUrl) {
            this.pictUrl = pictUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(String couponPrice) {
            this.couponPrice = couponPrice;
        }

        public String getAfterCouponPrice() {
            return afterCouponPrice;
        }

        public void setAfterCouponPrice(String afterCouponPrice) {
            this.afterCouponPrice = afterCouponPrice;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getUlandUrl() {
            return ulandUrl;
        }

        public void setUlandUrl(String ulandUrl) {
            this.ulandUrl = ulandUrl;
        }
    }
}
