package com.zhongjiang.dyn.http;

import android.content.Context;

import com.zhongjiang.dyn.file.FileStorageManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpManager {
    private static final HttpManager sManager = new HttpManager();
    private static final int NETWORK_CODE = 1;

    private Context mContext;

    private OkHttpClient mClient;

    public static HttpManager getInstance() {
        return sManager;
    }

    private HttpManager() {
        mClient = new OkHttpClient();
    }

    public void init(Context context) {
        this.mContext = context;
    }
    /**同步请求*/
    public Response synsRequest(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            return mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**异步下载请求*/
    public void synsRequest(final String url, final DownloadCallback callback) {
        Request request = new Request.Builder().url(url).build();
             mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (callback != null){
                        callback.fail(NETWORK_CODE,"网络请求失败");
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful() && callback != null){
                        callback.fail(NETWORK_CODE,"网络请求失败");
                    }
                    File file = FileStorageManager.getInstance().getFileByName(url);
                    byte[] buffer = new byte[1024*500];
                    int length;
                    FileOutputStream fileOut = new FileOutputStream(file);
                    InputStream inStream = response.body().byteStream();
                    while ((length = inStream.read(buffer,0,buffer.length)) != -1){
                        fileOut.write(buffer,0,length);
                        fileOut.flush();
                    }
                    callback.success(file);
                }
            });
    }
}
