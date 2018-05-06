package com.zhongjiang.dyn.file;

import android.content.Context;
import android.os.Environment;

import com.zhongjiang.dyn.utils.Md5Utils;

import java.io.File;
import java.io.IOException;

public class FileStorageManager {

    private static final  FileStorageManager sManger = new FileStorageManager();
    private Context mContext;
    public static FileStorageManager getInstance(){

        return sManger;
    }
    private FileStorageManager(){

    }
    public void init(Context context){
        this.mContext = context;
    }
    public File getFileByName(String url){
        File parent ;
        if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)){
            parent = mContext.getExternalCacheDir();
        }else {
            parent = mContext.getCacheDir();
        }
        String fileName = Md5Utils.generateCode(url);
        File file = new File(parent,fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
