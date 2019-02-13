package com.example.jh.taokelink.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jh.taokelink.BaseFragment;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.api.ApiSource;
import com.example.jh.taokelink.entity.SystemBean;
import com.example.jh.taokelink.http.BaseResponse;
import com.example.jh.taokelink.http.ResponseObserver;
import com.example.jh.taokelink.http.RxBus;
import com.example.jh.taokelink.utils.EventBus;
import com.example.jh.taokelink.utils.Keys;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author：rjhsmile
 * @PROJECT_NAME:TaoKeLink
 * @DATE：2018/3/1 17:11
 * @PACKAGE_NAME：MainFragment
 */

public class MainFragment extends BaseFragment {


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        RxBus.get().toFlowable(EventBus.class).subscribe(new Consumer<EventBus>() {
            @Override
            public void accept(EventBus eventType) throws Exception {
                if (Keys.EVENT_WHAT_SUCCESS == eventType.getType()) {
                    Disposable mDisposableInterval = Observable.interval(1, 1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.e(TAG, "accept: " + aLong);
                            initData();
                        }
                    });
                    mCompositeDisposable.add(mDisposableInterval);
                }
            }
        });
    }

    @Override
    public void initData() {
        Map<String, Object> map = new HashMap<>();
        ApiSource.getInstance().getSystem(map)
                //.compose(this.<BaseResponse<SystemBean>>bindUntilEvent(FragmentEvent.STOP))
                .subscribe(new ResponseObserver<BaseResponse<SystemBean>>(getActivity(), true) {
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

    @OnClick({R.id.id_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_button:
                RxBus.get().post(new EventBus(Keys.EVENT_WHAT_SUCCESS));
                break;
        }
    }

}
