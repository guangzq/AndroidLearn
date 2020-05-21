package com.zqg.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..10) {
            if (i % 2 == 0) {
                continue
            }
            println(i)
        }
    }

    override fun onResume() {
        super.onResume()
//        lifecycle.addObserver(ActivityD())
    }
}
