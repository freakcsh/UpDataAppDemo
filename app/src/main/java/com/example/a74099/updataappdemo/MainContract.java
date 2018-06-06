package com.example.a74099.updataappdemo;



import com.example.a74099.updataappdemo.bean.TextBean;
import com.example.a74099.updataappdemo.net.BasePresenter;
import com.example.a74099.updataappdemo.net.BaseView;

import java.util.List;

/**
 * Created by 74099 on 2018/5/9.
 */

public class MainContract {
    interface View extends BaseView {
        void getTestSuccess(TextBean list);

    }

    interface Presenter extends BasePresenter<View> {
        void getText(String s);
    }
}
