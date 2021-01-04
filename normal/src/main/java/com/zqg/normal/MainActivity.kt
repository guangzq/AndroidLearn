package com.zqg.normal

import android.app.IntentService
import android.content.*
import android.content.Intent.ACTION_GET_CONTENT
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.util.ArrayMap
import android.util.Log
import android.util.SparseArray
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLConnection

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "key_service"
    }

    var onService: ServiceConnection = OnService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, LearnService::class.java)
        btn.setOnClickListener {
            Thread.currentThread().id
            intent.putExtra(KEY, "this is a text")
            bindService(intent, onService, BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(onService)
    }

    class OnService : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val learnService = service as LearnService.MyBinder
            val service1 = learnService.service
            Log.d("OnService", service1.data)

        }

    }
}
