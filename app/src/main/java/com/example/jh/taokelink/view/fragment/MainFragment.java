package com.example.jh.taokelink.view.fragment;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.OkhttpSource;
import com.example.jh.taokelink.entity.RiderBean;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.ResponseObserver;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 17:11
 * @PACKAGE_NAME：MainFragment
 */

public class MainFragment extends BaseFragment {
    @Override
    protected void initView() {

        OkhttpSource.getInstance().getSalesSellerEntryList("3", "63", "13521653231", "2222")
                .subscribe(new ResponseObserver<BaseResponse<RiderBean>>(getActivity(), true) {

                    @Override
                    public void onSuccess(BaseResponse<RiderBean> riderBeanBaseResponse) {

                    }
                });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.empty_view;
    }
}
