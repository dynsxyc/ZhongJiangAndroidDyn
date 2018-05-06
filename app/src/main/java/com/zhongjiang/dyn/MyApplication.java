package com.zhongjiang.dyn;

import android.app.Application;

import com.zhongjiang.dyn.file.FileStorageManager;
import com.zhongjiang.dyn.http.HttpManager;
import com.zhongjiang.dyn.utils.Logger;

import java.io.File;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileStorageManager.getInstance().init(this);
        HttpManager.getInstance().init(this);
    }
}
