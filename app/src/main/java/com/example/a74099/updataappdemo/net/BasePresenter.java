package com.example.a74099.updataappdemo.net;

/**
 * Created by Administrator on 2018/2/4.
 */

public interface  BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
