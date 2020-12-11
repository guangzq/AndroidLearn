package com.zqg.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val myHandler = MyHandler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val runnable = Runnable {
            Thread.sleep(10 * 1000)
            val message = Message()
            message.arg1 = 1
            message.what = 2
            myHandler.sendMessage(message)
        }
        btn.setOnClickListener {
            val thread = Thread(runnable)
            thread.start()
        }

    }

    class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what) {
                2->{
                    Log.d("MainActivity", msg.arg1.toString())

                }
            }
        }
    }
}
