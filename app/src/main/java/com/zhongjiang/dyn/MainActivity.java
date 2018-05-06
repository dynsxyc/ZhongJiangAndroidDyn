package com.zhongjiang.dyn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zhongjiang.dyn.file.FileStorageManager;
import com.zhongjiang.dyn.http.DownloadCallback;
import com.zhongjiang.dyn.http.HttpManager;
import com.zhongjiang.dyn.utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file =  FileStorageManager.getInstance().getFileByName("http://www.zhogjiang.com");
        Logger.debug("test","file path =  "+file.getAbsoluteFile());
        final ImageView imageView = findViewById(R.id.imageView);
        HttpManager.getInstance().synsRequest("http://img3.imgtn.bdimg.com/it/u=1280789495,3306254756&fm=27&gp=0.jpg", new DownloadCallback() {
            @Override
            public void success(File file) {
                Logger.debug("dong",file.getAbsolutePath());
                final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void fail(int errorCode, String errorMessage) {
                Logger.debug("dong",errorMessage);
            }

            @Override
            public void progress(int progress) {

            }
        });
    }
}
