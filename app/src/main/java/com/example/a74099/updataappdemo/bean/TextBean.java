package com.example.a74099.updataappdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 74099 on 2018/5/9.
 */

public class TextBean implements Serializable {

    private boolean versionControl;

    private int versionCode;

    private String versionName;

    private String downloadLink;

    private String changeLog;

    private boolean constraint;

    private boolean size;

    public void setVersionControl(boolean versionControl) {
        this.versionControl = versionControl;
    }

    public boolean getVersionControl() {
        return this.versionControl;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return this.downloadLink;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getChangeLog() {
        return this.changeLog;
    }

    public void setConstraint(boolean constraint) {
        this.constraint = constraint;
    }

    public boolean getConstraint() {
        return this.constraint;
    }

    public void setSize(boolean size) {
        this.size = size;
    }

    public boolean getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "TextBean{" +
                "versionControl=" + versionControl +
                ", versionCode=" + versionCode +
                ", versionName=" + versionName +
                ", downloadLink='" + downloadLink + '\'' +
                ", changeLog='" + changeLog + '\'' +
                ", constraint=" + constraint +
                ", size=" + size +
                '}';
    }
}
