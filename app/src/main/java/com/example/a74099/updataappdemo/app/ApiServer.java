package com.example.a74099.updataappdemo.app;



import com.example.a74099.updataappdemo.bean.TextBean;
import com.example.a74099.updataappdemo.net.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiServer {

    @GET("shopmobi")
    Observable<HttpResult<TextBean>> getCoin(@Query("s") String s);


}
