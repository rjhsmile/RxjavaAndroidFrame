package com.example.jh.taokelink.entity;

import java.util.List;

/**
 * Created by x on 2016/9/25.
 */
public class Categorys {

    /**
     * ret : 1
     * msg : 成功
     * data : [{"id":0,"name":"qianyan.mp4","url":"https://bj.bcebos.com/course-mct/media/qianyan.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F109a94f60b1819c2206943c8b4f01bd3a0437e6083a99c6c13bcb042a2ec7220","title":"前言"},{"id":1,"name":"riwenhanzi.mp4","url":"https://bj.bcebos.com/course-mct/media/riwenhanzi.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F35a3d43ab622cfb4cab662ad549721767d494b0d76ebf831fee691bc9e7aea05","title":"五十音图\u2014\u2014日文构成"},{"id":2,"name":"a.mp4","url":"https://bj.bcebos.com/course-mct/media/a.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F32a59acdc1330ee889d5b4201a50de47fb2e150432e7031b0d5d66cf4bc04ca8","title":"五十音图\u2014\u2014あ行"},{"id":3,"name":"ka.mp4","url":"https://bj.bcebos.com/course-mct/media/ka.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2Fb3e35ae4b9df97c99c33fda1fe5ddcd045f2ebe71e35941e12346c2f9b4fbcdc","title":"五十音图\u2014\u2014か行"},{"id":4,"name":"sa.mp4","url":"https://bj.bcebos.com/course-mct/media/sa.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F56d60668fc9b978e3478c7b707700c4b026f0729588d9dd98de624bd8105fd9b","title":"五十音图\u2014\u2014さ行"},{"id":5,"name":"ta.mp4","url":"https://bj.bcebos.com/course-mct/media/ta.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2Fcf3a03d57d16d2de336e5ce73570588c369267eb9554647cc975e0a51757ac83","title":"五十音图\u2014\u2014た行"},{"id":6,"name":"na.mp4","url":"https://bj.bcebos.com/course-mct/media/na.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F8219807fbfbd50a6049874367eaf39e96086858d6362b92dd92a00ef665c23a8","title":"五十音图\u2014\u2014な行"},{"id":7,"name":"ha.mp4","url":"https://bj.bcebos.com/course-mct/media/ha.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F5f0bb9133d85f8263622ca7dca419286c21ddd8044d900aea06ffad8420a783e","title":"五十音图\u2014\u2014は行"},{"id":8,"name":"ma.mp4","url":"https://bj.bcebos.com/course-mct/media/ma.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F88b0d9ca100d66141f9d73a71763f8dd74368e11349afb86941a4a8e9e92d998","title":"五十音图\u2014\u2014ま行"},{"id":9,"name":"ya.mp4","url":"https://bj.bcebos.com/course-mct/media/ya.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F0936108ee01ac16666a48034bb382e6eaa9c6381ca3adfd9bafb408cb655c8df","title":"五十音图\u2014\u2014や行"},{"id":10,"name":"ra.mp4","url":"https://bj.bcebos.com/course-mct/media/ra.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2Febf88ec9606ed59743ba1e227664d2b09f4e2a4f423625d14d02cb8e1a42d5ad","title":"五十音图\u2014\u2014ら行"},{"id":11,"name":"wa.mp4","url":"https://bj.bcebos.com/course-mct/media/wa.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F10683260f818b0547fc702c6888585a244fcf09410ae798a9228954cbb4b1eea","title":"五十音图\u2014\u2014わ行"},{"id":12,"name":"boyin.mp4","url":"https://bj.bcebos.com/course-mct/media/boyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A23Z%2F6000%2F%2F155245d31d132be6e0b84f9f9fd2b5f253a20670ea2d08b926019bcd001d4f9d","title":"五十音图\u2014\u2014拨音"},{"id":13,"name":"zhuoyin.mp4","url":"https://bj.bcebos.com/course-mct/media/zhuoyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A23Z%2F6000%2F%2F682ff5b80462bf9b9ec43e6bc420ca9ee0665de0c43a412b5b711da0f02c9b9f","title":"五十音图\u2014\u2014浊音"},{"id":14,"name":"000%2F%2F1739becd821a1a144bd07f66880819c032c77eee162977154d8387d0215b425d","title":"五十音图\u2014\u2014促音"},{"id":15,"name":"changyin.mp4","url":"https://bj.bcebos.com/course-mct/media/changyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A23Z%2F6000%2F%2F18e3089095ba8569aa89b4f0c6aebd0a6cec801fc56379f315ac4e73ed297c8b","title":"五十音图\u2014\u2014长音"},{"id":16,"name":"aoyin.mp4","url":"https://bj.bcebos.com/course-mct/media/aoyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A23Z%2F6000%2F%2F8044e2998b0cabab964fb3f83b91462956144363df8642a49e8d45ed99a521b7","title":"五十音图\u2014\u2014拗音"},{"id":17,"name":"yindiao.mp4","url":"https://bj.bcebos.com/course-mct/media/yindiao.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A23Z%2F6000%2F%2F033238cc34296292967a34ccabce5d196454fc6e424050a8f0a529ff2da2407a","title":"五十音图\u2014\u2014音调"},{"id":18,"name":"50yinjieshu.mp4","url":"https://bj.bcebos.com/course-mct/media/50yinjieshu.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A23Z%2F6000%2F%2Fbeda1504b1bae1878b8506e6d750c848799b6b637e31f7d28169b55e48f0717e","title":"结束"}]
     */

    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 0
         * name : qianyan.mp4
         * url : https://bj.bcebos.com/course-mct/media/qianyan.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2018-08-23T07%3A13%3A22Z%2F6000%2F%2F109a94f60b1819c2206943c8b4f01bd3a0437e6083a99c6c13bcb042a2ec7220
         * title : 前言
         */

        private int id;
        private String name;
        private String url;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
