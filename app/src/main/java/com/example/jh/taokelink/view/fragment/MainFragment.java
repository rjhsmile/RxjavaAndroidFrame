package com.example.jh.taokelink.view.fragment;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.ApiSource;
import com.example.jh.taokelink.entity.SystemBean;
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

        ApiSource.getInstance().getSalesSellerEntryList().subscribe(
                new ResponseObserver<BaseResponse<SystemBean>>(getActivity(), true) {
                    @Override
                    public void onSuccess(BaseResponse<SystemBean> riderBeanBaseResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.empty_view;
    }
}
