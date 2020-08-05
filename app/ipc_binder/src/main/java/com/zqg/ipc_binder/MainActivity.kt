package com.zqg.ipc_binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.zqg.ipc_binder.NumService.LocalBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mBind: Boolean? = false
    var mService: NumService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, NumService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        binderBtn.setOnClickListener {
            mBind?.apply {
                Toast.makeText(this@MainActivity, "random " + mService?.getRandomNum(), Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }

    val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBind = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val localBinder = service as LocalBinder
            mService = localBinder.getService()
            mBind = true
        }

    }
}
