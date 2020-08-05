package com.test.testtdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/5 14:46
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class Num implements Parcelable {
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    public void readFromParcel(Parcel parcel) {
        id = parcel.readInt();
    }

    public Num() {
    }

    protected Num(Parcel in) {
        this.id = in.readInt();
    }

    public static final Creator<Num> CREATOR = new Creator<Num>() {
        @Override
        public Num createFromParcel(Parcel source) {
            return new Num(source);
        }

        @Override
        public Num[] newArray(int size) {
            return new Num[size];
        }
    };
}
