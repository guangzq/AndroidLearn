package com.zqg.normal

import android.os.Build
import android.os.SharedMemory

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2021/1/18 10:44
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class SharedMemoryUtil {
    companion object {
        @JvmStatic
        private var create: SharedMemory? = null

        fun create(str: String): SharedMemory? {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                if (create == null) {
                    create = SharedMemory.create(MainActivity.CREATE_NAME, str.length)
                }
                return create
            }
            return null
        }
    }

}