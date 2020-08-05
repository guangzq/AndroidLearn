package com.zqg.ipc_aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/5 15:00
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class Book implements Parcelable {
    private String author;
    private int num;

    public Book(String author, int num) {
        this.author = author;
        this.num = num;
    }

    protected Book(Parcel in) {
        author = in.readString();
        num = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeInt(num);
    }

    public void readFromParcel(Parcel parcel) {
        author = parcel.readString();
        num = parcel.readInt();
    }

}
