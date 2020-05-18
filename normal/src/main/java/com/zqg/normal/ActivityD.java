package com.zqg.normal;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/12 15:07
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ActivityD implements LifecycleObserver {
    private static final String TAG = "ActivityD";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreated(LifecycleOwner owner) {
        Log.d(TAG, "onCreated" + owner.getLifecycle().getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void ON_RESUME(LifecycleOwner owner) {
        Log.d(TAG, "ON_RESUME" + owner.getLifecycle().getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void ON_PAUSE(LifecycleOwner owner) {
        Log.d(TAG, "ON_PAUSE" + owner.getLifecycle().getCurrentState().name());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void ON_DESTROY(LifecycleOwner owner) {
        Log.d(TAG, "ON_DESTROY" + owner.getLifecycle().getCurrentState().name());
    }

}
