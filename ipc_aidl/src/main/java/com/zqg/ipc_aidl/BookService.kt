package com.zqg.ipc_aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/5 15:10
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class BookService : Service() {
    var mBookList = mutableListOf<Book>()

    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book("三国演义", 10))
        mBookList.add(Book("红楼梦", 20))
        mBookList.add(Book("android", 50))
        mBookList.add(Book("ios", 6))
    }

    var bookController = object : BookController.Stub() {
        override fun addBook(book: Book?) {
            book?.apply {
                book.author = "作者被修改了"
                book.num = 2
                mBookList.add(book)
            }
        }

        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

    }


    override fun onBind(intent: Intent?): IBinder? {
        return bookController
    }
}