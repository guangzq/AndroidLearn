package com.zqg.dispatch_event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/18 15:27
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ParentView extends ScrollView {
    private String TAG = ParentView.class.getSimpleName();

    public ParentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent " +  event.toString());
        boolean result = super.dispatchTouchEvent(event);
        Log.d(TAG, "dispatchTouchEvent result " +  result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptTouchEvent " +  event.toString());
        boolean result = super.onInterceptTouchEvent(event);
        Log.d(TAG, "onInterceptTouchEvent result " +  result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
