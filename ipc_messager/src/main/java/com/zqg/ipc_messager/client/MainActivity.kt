package com.zqg.ipc_messager.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.zqg.ipc_messager.R
import com.zqg.ipc_messager.server.MessengerService
import kotlinx.android.synthetic.main.activity_main.*
import utils.Contants
import utils.Contants.Companion.MESSAGE_STRING

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG  = "MainActivity client"
    }
    var mReplyMessenger : Messenger? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mReplyMessenger = Messenger(ClientHandler())
        bindService()
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val message = Message()
                message.what = Contants.MESSAGE_TAG
                val bundle = Bundle()
                bundle.putString(MESSAGE_STRING, s.toString())
                message.data = bundle
                message.replyTo = mReplyMessenger
                mMessenger?.send(message)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }



    class ClientHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what) {
                Contants.MESSAGE_TAG-> {
                    val data = msg.data
                    val messageStr = data.getString(MESSAGE_STRING)
                    Log.d(TAG, messageStr)
                }
            }
        }
    }

    private  var mMessenger: Messenger? = null
    val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mMessenger = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMessenger = Messenger(service)
        }

    }

    fun bindService() {
        val intent = Intent(this, MessengerService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }
}
