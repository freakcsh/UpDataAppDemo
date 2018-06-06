package com.example.a74099.updataappdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.a74099.updataappdemo.base.BaseActivity;
import com.example.a74099.updataappdemo.bean.TextBean;

import java.util.List;

import customview.ConfirmDialog;
import feature.Callback;
import util.UpdateAppUtils;

/**
 * Created by 74099 on 2018/6/6.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    //服务器apk path
    String apkPath = "http://www.nyc929.com/nbsc.apk";
    private int code = 0;
    private TextBean textBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getText("Default.UpgradeVersion");
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    public void updateApp(View view) {
        checkAndUpdate(1);
    }

    public void downloadByWeb(View view) {
        checkAndUpdate(2);
    }


    public void forceUpdate(View view) {
        checkAndUpdate(3);
    }


    public void checkByName(View view) {
        checkAndUpdate(4);
    }

    public void kotlin(View view) {
        checkAndUpdate(5);
    }

    private void checkAndUpdate(int code) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            realUpdate(code);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                realUpdate(code);
            } else {//申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }


    private void realUpdate(int code) {
        this.code = code;
        switch (code) {
            case 1:
                updat1();
                break;
            case 2:
                update2();
                break;
            case 3:
                update3();
                break;
            case 4:
                update4();
                break;
            case 5:
                startActivity(new Intent(this, KotlinMainActivity.class));
                break;

        }
    }


    //基本更新
    private void updat1() {
        if (textBean != null) {
            UpdateAppUtils.from(this)
                    .serverVersionCode(textBean.getVersionCode())
                    .serverVersionName(textBean.getVersionName())
                    .apkPath(textBean.getDownloadLink())
                    .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
//                .showNotification(false)
//                .needFitAndroidN(false)
                    .update();
        }

    }

    //通过浏览器下载
    private void update2() {
        if (textBean != null) {
            UpdateAppUtils.from(this)
                    .serverVersionCode(textBean.getVersionCode())
                    .serverVersionName(textBean.getVersionName())
                    .apkPath(textBean.getDownloadLink())
                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER)
                    .update();
        }

    }

    //强制更新
    private void update3() {
        if (textBean != null) {
            UpdateAppUtils.from(this)
                    .serverVersionCode(textBean.getVersionCode())//apk版本号
                    .serverVersionName(textBean.getVersionName())//apk版本名
                    .apkPath(textBean.getDownloadLink())//apk地址
                    .isForce(true)//是否强制更新
                    .update();
        }

    }

    //根据versionName判断跟新
    private void update4() {
        if (textBean != null) {
            UpdateAppUtils.from(this)
                    .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME)
                    .serverVersionName(textBean.getVersionName())
                    .serverVersionCode(textBean.getVersionCode())
                    .apkPath(textBean.getDownloadLink())
                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER)
                    .isForce(true)
                    .update();
        }

    }


    //权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    realUpdate(code);
                } else {
                    new ConfirmDialog(this, new Callback() {
                        @Override
                        public void callback(int position) {
                            if (position == 1) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                                startActivity(intent);
                            }
                        }
                    }).setContent("暂无读写SD卡权限\n是否前往设置？").show();
                }
                break;
        }

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void getTestSuccess(TextBean list) {
        textBean = list;
    }

}
