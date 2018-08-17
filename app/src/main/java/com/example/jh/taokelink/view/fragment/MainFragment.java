package com.example.jh.taokelink.view.fragment;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.ApiSource;
import com.example.jh.taokelink.entity.CopyBean;
import com.example.jh.taokelink.http.BaseArrayResponse;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.ResponseObserver;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 17:11
 * @PACKAGE_NAME：MainFragment
 */

public class MainFragment extends BaseFragment {
    @Override
    protected void initView() {
        Map<String,Object> map=new HashMap<>();
        map.put("pasteContent", "caa65cb4c173f4f661406cef515acc36");
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

       /* ApiSource.getInstance().getSalesSellerEntryList(map).subscribe(
                new ResponseObserver<BaseArrayResponse<String>>(getActivity(), true) {
                    @Override
                    public void onSuccess(BaseArrayResponse<String> riderBeanBaseResponse) {

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
}
