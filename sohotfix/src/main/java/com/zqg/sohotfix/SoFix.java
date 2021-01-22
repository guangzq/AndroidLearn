package com.zqg.sohotfix;

import android.content.Context;
import android.os.Build;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2021/1/21 11:48
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class SoFix {
    private final Context mContext;
    private ISoFix soFix;

    public SoFix(Context context) {
        this.mContext = context.getApplicationContext();
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.O) {
            soFix = new SoFixImlV26(mContext);
        }else if (sdkInt >= Build.VERSION_CODES.M) {
            soFix = new SoFixImlV23(mContext);
        }else {
            soFix = new SoFixImlV21(mContext);
        }
    }

    public void inject(String soPath) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        soFix.injectSo(soPath);
    }
}
