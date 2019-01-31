package com.example.jh.taokelink.view.fragment;

import android.widget.Toast;

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
import com.example.jh.taokelink.utils.ToastUtils;

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
        Map<String, Object> map = new HashMap<>();
        ApiSource.getInstance().getSystem(map).subscribe(new ResponseObserver<BaseResponse<SystemBean>>(getActivity(), true) {
            @Override
            public void onSuccess(BaseResponse<SystemBean> response) {
                Toast.makeText(getActivity(), response.msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ApiException e) {
                ToastUtils.show(e.meg);
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.empty_view;
    }


}
