package com.example.jh.taokelink.view.fragment;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.ApiService;
import com.example.jh.taokelink.api.ApiSource;
import com.example.jh.taokelink.entity.Categorys;
import com.example.jh.taokelink.entity.CopyBean;
import com.example.jh.taokelink.http.BaseArrayResponse;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.ResponseObserver;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.Md5Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 17:11
 * @PACKAGE_NAME：MainFragment
 */

public class MainFragment extends BaseFragment {
    @Override
    protected void initView() {
        Map<String, Object> map = new HashMap<>();
        map.put("pasteContent", "復·制这段描述，€S2lHbXGwILO€ ，咑閞【手机淘宝】即可查看");
        ApiSource.getInstance().getCopyData(map).subscribe(
                new ResponseObserver<BaseResponse<CopyBean>>(getActivity(), true) {
                    @Override
                    public void onSuccess(BaseResponse<CopyBean> riderBeanBaseResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

       /* ApiSource.getInstance().getSalesSellerEntryList(0,20).subscribe(
                new ResponseObserver<BaseResponse<Categorys>>(getActivity(), true) {
                    @Override
                    public void onSuccess(BaseResponse<Categorys> response) {
                        Categorys mRespose = response.data;
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });*/
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.empty_view;
    }

    /**
     * 获取字符串+签名
     *
     * @param request
     * @return
     */
    private String singnParam(Request request) {
        HttpUrl url = request.url();
        String scheme = url.scheme();//  http https
        String host = url.host();//   127.0.0.1
        String path = url.encodedPath();//  /test/upload/img
        String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345

        //创建StringBuffer准备拼接参数
        StringBuffer sb = new StringBuffer();
        //sb.append(scheme).append(host).append(path).append("?");
        Set<String> queryList = url.queryParameterNames();
        Iterator<String> iterator = queryList.iterator();
        //参数拼接
        for (int i = 0; i < queryList.size(); i++) {
            String queryName = iterator.next();
            String queryKey = url.queryParameter(queryName);
            sb.append(queryName).append(queryKey);
           /* if (iterator.hasNext()) {
                sb.append("&");
            }*/
        }
        sb.append("appkey").append(Keys.appkey).append("os").append("android")
                .append("t").append(String.valueOf(System.currentTimeMillis()))
                .append("v").append("1").append(Keys.appsecret);

        //ParameterNames和ParameterKey拼接进行加密
        String newUrl = sb.toString();
        return Md5Util.md5(newUrl);
    }

}
