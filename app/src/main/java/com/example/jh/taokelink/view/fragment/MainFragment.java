package com.example.jh.taokelink.view.fragment;

import android.widget.Toast;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.ApiSource;
import com.example.jh.taokelink.entity.SystemBean;
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
        Map<String, Object> map = new HashMap<>();
        ApiSource.getInstance().getSystem(map).subscribe(new ResponseObserver<BaseResponse<SystemBean>>(getActivity(), true) {
            @Override
            public void onSuccess(BaseResponse<SystemBean> response) {
                Toast.makeText(getActivity(), response.msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int code, String message) {
                Toast.makeText(getActivity(), code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.empty_view;
    }


}
