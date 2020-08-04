package com.zqg.ipc_messager.server;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import utils.Contants;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/3 18:21
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class MessengerService extends Service {
    public static final String TAG = "MessengerService";
    private static Messenger messager = new Messenger(new MessageHandler());
    private static class MessageHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == Contants.MESSAGE_TAG) {
                Message message = new Message();
                Messenger messenger = msg.replyTo;
                Bundle data = msg.getData();
                String messageStr = data.getString(Contants.MESSAGE_STRING);
                Log.d(TAG, "reciver some message " + messageStr);
                data.putString(Contants.MESSAGE_STRING, messageStr + "加点料");
                message.what = Contants.MESSAGE_TAG;
                message.setData(data);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messager.getBinder();
    }
}
