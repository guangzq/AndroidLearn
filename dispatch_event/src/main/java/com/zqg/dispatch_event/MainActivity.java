package com.zqg.dispatch_event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoller);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent " +  event.toString());
        boolean result = super.dispatchTouchEvent(event);
        Log.d(TAG, "dispatchTouchEvent result " +  result);
        return result;
    }

}
