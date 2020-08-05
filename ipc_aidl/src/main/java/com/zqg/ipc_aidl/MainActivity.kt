package com.zqg.ipc_aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, BookService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        getBtn.setOnClickListener {
            val bookList = mBookController?.bookList
            val stringBuilder = StringBuilder()
            bookList?.also {
                for (book in bookList) {
                    stringBuilder.append(book.author + " 有" + book.num + "\n")
                }
            }
            showTv.text = stringBuilder
        }
        addBtn.setOnClickListener {
            Toast.makeText(this, "增加了本新书", Toast.LENGTH_SHORT).show()
            mBookController?.addBook(Book("这是没改名的新书", 1))
        }
    }

    private var mBookController: BookController? = null
    val serviceConnection = object :ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBookController = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBookController = BookController.Stub.asInterface(service)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }
}
