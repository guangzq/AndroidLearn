package com.zqg.normal

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.SharedMemory
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zqg.normal.MainActivity.Companion.CREATE_NAME
import kotlinx.android.synthetic.main.activity_second.*
import java.io.Serializable

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2021/1/18 10:40
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
@ExperimentalStdlibApi
class SecondActivity : AppCompatActivity(),Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        getDataBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//                val create = SharedMemoryUtil.create("create a")
                val create = SharedMemory.create(MainActivity.CREATE_NAME, "create a".length)
                val mapReadWrite = create!!.mapReadOnly()
                val length = mapReadWrite.limit() - mapReadWrite.position()
                val byteArray = ByteArray(length)
                mapReadWrite.get(byteArray)
                Toast.makeText(this, byteArray.decodeToString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}