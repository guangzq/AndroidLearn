package com.zqg.sohotfix

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 100)
    }

    fun dynamic(view: View) {
        val file = File(Environment.getExternalStorageDirectory(), "so/libmain.so")
        val libSoPath = File(externalCacheDir, "so")
        Log.d(TAG, libSoPath.absolutePath)
        if (!libSoPath.exists()) {
            libSoPath.mkdirs()
        }
        val libSoFile = File(libSoPath, "libmain.so")
        FileUtil.copyFile(file, libSoFile)
        Log.d(TAG, libSoFile.absolutePath)
        val soFix = SoFix(this)
        soFix.inject(libSoPath.absolutePath)
    }
    fun loadSo(view: View) {
        val gameSDK = GameSDK()
    }
}
