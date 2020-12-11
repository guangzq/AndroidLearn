package com.zqg.okhttp_read2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.internal.cache.CacheInterceptor
import okhttp3.internal.http.RetryAndFollowUpInterceptor
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object {
        const val URL = "https://wanandroid.com/wxarticle/chapters/json"
        const val TAG = "okhttp_test"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        okhttpBtn.setOnClickListener {
            request()
        }
    }

    private fun getClient() : OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun request() {
        val cache = Cache(File("test"), 2 * 1024 * 1024)
        val okHttpClient = OkHttpClient.Builder()
                //添加拦截器
                .addInterceptor(LoggingInterceptor())
//                .addInterceptor(CacheInterceptor(cache))
//                .addInterceptor(RetryAndFollowUpInterceptor(getClient()))
//                .eventListener(EventListener.NONE)
                .build()

        val builder = Request.Builder().url(URL)
                .build()
        val newCall = okHttpClient.newCall(builder)
        if (newCall.isExecuted()) newCall.cancel()
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e(TAG, response.body?.string())
            }

        })
    }

    private inner class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            Log.e(TAG, "请求前")
            val request = chain.request()
            val proceed = chain.proceed(request)
            Log.e(TAG, "请求后" + proceed.headers.name(0))
            return proceed
        }

    }
}
