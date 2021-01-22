package com.zqg.sohotfix;

import android.util.Log;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2021/1/21 16:29
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class GameSDK {
    static {
        System.loadLibrary("main");
    }
    public GameSDK() {
        Log.d("GameSDK", "ok!!!!!");
    }
}
