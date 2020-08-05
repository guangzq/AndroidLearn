// BookController.aidl
package com.zqg.ipc_aidl;
import com.zqg.ipc_aidl.Book;

// Declare any non-default types here with import statements

interface BookController {
    List<Book> getBookList();

    //注意此处inout作用
    void addBook(inout Book book);
}
