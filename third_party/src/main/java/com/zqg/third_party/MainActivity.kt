package com.zqg.third_party

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)
        main_tv.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        main_reg.setOnClickListener {
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public fun onMessageEvent(event: StringEvent) {
//        main_tv.text = event.text
        Log.d(TAG, Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
