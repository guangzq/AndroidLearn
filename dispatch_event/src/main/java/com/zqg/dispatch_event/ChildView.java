package com.zqg.dispatch_event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/18 15:25
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ChildView extends AppCompatButton {
    private String TAG = ChildView.class.getSimpleName();

    public ChildView(Context context, @Nullable AttributeSet attrs) {
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
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
