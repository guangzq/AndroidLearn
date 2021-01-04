package com.test;

import android.app.Application;
import android.content.Context;
import android.os.Debug;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/3 16:54
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class MyApplication extends Application {

   @Override
    public void onCreate() {
        super.onCreate();
//        Debug.waitForDebugger();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
