package com.zqg.leak;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;
import androidx.core.util.LruCacheKt;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import leakcanary.AppWatcher;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/12/25 17:30
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick2();
            }
        });
    }

    private void onClick2() {
        ReferenceQueue<Object> mRq;
        WeakReference<Num> mWeakReference = new WeakReference<>(new Num(126), mRq = new ReferenceQueue<>());
        Log.d(TAG, "GC前," + "num=" + mWeakReference.get().i + ",mRq=" + mRq.poll());
        Runtime.getRuntime().gc();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "GC后," + "num=" + mWeakReference.get() + ",mRq=" + mRq.poll());
    }

    static class Num {
        int i;
        public Num(int i) {
            this.i = i;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            Log.d(TAG, "finalize->" + i);
        }
    }


}
