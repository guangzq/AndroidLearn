package com.test.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/3 15:32
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class AIDLService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
