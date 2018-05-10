package com.zhongjiang.dyn.opensource;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dyn on 2018/5/10.
 */

public class ToastUtils {
    /**
     * 显示吐司
     *
     * @param context
     * @param str
     */
    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
