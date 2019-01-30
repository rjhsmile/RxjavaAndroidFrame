package com.example.jh.taokelink.view.fragment;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.Constants;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.ApiSource;
import com.example.jh.taokelink.entity.Categorys;
import com.example.jh.taokelink.entity.SystemBean;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.ResponseObserver;
import com.example.jh.taokelink.http.exception.ApiException;
import com.example.jh.taokelink.utils.Keys;
import com.example.jh.taokelink.utils.Md5Util;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 17:11
 * @PACKAGE_NAME：MainFragment
 */

public class MainFragment extends BaseFragment {
    @Override
    protected void initView() {

      /*  ApiSource.getInstance().getCopyData(1).subscribe(
                new ResponseObserver<BaseResponse<String>>(getActivity(), true) {
                    @Override
                    public void onSuccess(BaseResponse<String> copyBeanBaseResponse) {

                    }

                    @Override
                    public void onError(ApiException e) {
                        //Toast.makeText(getActivity(),e.toString()+e.code,Toast.LENGTH_SHORT).show();
                    }
                });*/

      /*  ApiSource.getInstance().getSalesSellerEntryList(0, 20).subscribe(
                new ResponseObserver<BaseResponse<Categorys>>(getActivity(), true) {
                    @Override
                    public void onSuccess(BaseResponse<Categorys> response) {
                        Categorys mRespose = response.data;
                    }

                    @Override
                    public void onError(ApiException e) {

                    }
                });*/

        Map<String, Object> map = new HashMap<>();
        map.put("appId", Constants.AppId);
        map.put("platform","1");
        map.put("timestamp",String.valueOf(System.currentTimeMillis()));
        map.put("version","1");
        map.put("sign",sign());
        ApiSource.getInstance().getSystem(map ).subscribe(new ResponseObserver<BaseResponse<SystemBean>>(getActivity(), true) {
            @Override
            public void onSuccess(BaseResponse<SystemBean> systemBeanBaseResponse) {

            }
            @Override
            public void onError(ApiException e) {

            }
        });
    }

    private String sign() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("appId=").append(Constants.AppId)
                .append("&platform=").append("1")
                .append("&timestamp=").append(String.valueOf(System.currentTimeMillis()))
                .append("&version=").append("1")
                .append("&api_token=").append(Constants.ApiToken);
        return Md5Util.md5(buffer.toString());
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.empty_view;
    }


}
