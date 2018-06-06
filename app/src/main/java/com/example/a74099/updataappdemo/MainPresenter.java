package com.example.a74099.updataappdemo;

import android.util.Log;


import com.example.a74099.updataappdemo.app.ApiServer;
import com.example.a74099.updataappdemo.bean.TextBean;
import com.example.a74099.updataappdemo.net.ApiCallback;
import com.example.a74099.updataappdemo.net.HttpMethods;
import com.example.a74099.updataappdemo.net.HttpResultFunc;
import com.example.a74099.updataappdemo.net.RxPresenter;
import com.example.a74099.updataappdemo.net.SubscriberCallBack;

import rx.Observable;

/**
 * Created by 74099 on 2018/5/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getText(String s) {
        Observable observable = apiServer.getCoin(s).map(new HttpResultFunc<TextBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<TextBean>() {

            @Override
            public void onSuccess(TextBean model) {
                mView.getTestSuccess(model);
                Log.e("freak", "请求成功"+model.toString());
            }

            @Override
            public void onFailure(String msg) {
                Log.e("freak","错误消息"+ msg);
            }
        }));
    }

}
