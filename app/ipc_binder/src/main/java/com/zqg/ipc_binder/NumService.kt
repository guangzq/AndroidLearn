package com.zqg.ipc_binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/4 17:19
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class NumService: Service() {
    private val binder = LocalBinder()
    private val mGer: Random = Random()


    fun getRandomNum(): Int {
        return mGer.nextInt(100)
    }

    inner class LocalBinder: Binder() {
        fun getService(): NumService = this@NumService
    }


    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
}