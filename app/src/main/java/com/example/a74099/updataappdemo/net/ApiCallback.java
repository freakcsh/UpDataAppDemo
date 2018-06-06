package com.example.a74099.updataappdemo.net;

public interface ApiCallback<T> {
    void onSuccess(T model);

    void onFailure(String msg);
}
