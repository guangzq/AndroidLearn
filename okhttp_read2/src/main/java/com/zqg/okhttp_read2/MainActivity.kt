package com.zqg.okhttp_read2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
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

    private fun request() {
        val okHttpClient = OkHttpClient()
        val builder = Request.Builder().url(URL).build()
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
}
