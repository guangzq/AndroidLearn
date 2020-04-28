package com.zqg.hotfixdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zqg.hotfixdemo.databinding.ActivityHotFixBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ActivityHotFixBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHotFixBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.oriBtn.setOnClickListener(this);
        mBinding.showFix.setOnClickListener(this);

        System.out.println("ReflectionTest==");
        Log.d("ReflectionTest", "main");
        try {
            OtherBean.class.getDeclaredMethod("testLog").invoke(OtherBean.class.newInstance());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            System.out.println("getClassLoader:" + classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("getClassLoader:" + classLoader);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.oriBtn:
                SaySuccess saySuccess = new SaySuccess();
                mBinding.showTv.setText(saySuccess.say());
                break;
            case R.id.showFix:
//                mBinding.showTv.setText(mSaySomething.say());
//                classLoader.pathList.dexElements = ???
                File newFile = new File(getCacheDir() + "/hotfix-debug.apk");
                try {
                    InputStream is = getAssets().open("apk/hotfix-debug.apk");
                    FileOutputStream fos = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    int byteCount = 0;
                    while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                        fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                    }
                    fos.flush();//刷新缓冲区
                    is.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
                    pathListField.setAccessible(true);
                    Object pathListObj = pathListField.get(getClassLoader());
                    Class<?> dexPathListClass = pathListObj.getClass();
                    Field dexElementsField = dexPathListClass.getDeclaredField("dexElements");
                    dexElementsField.setAccessible(true);
                    Object dexElementsObj = dexElementsField.get(pathListObj);
                    PathClassLoader pathClassLoader = new PathClassLoader(newFile.getPath(), null);
                    Object newPathListObj = pathListField.get(pathClassLoader);
                    Object newDexElementsObj = dexElementsField.get(newPathListObj);
                    //因为方法数会增加，apk体积增大，所以只针对修改过的类add dex
                    int oldLength = Array.getLength(dexElementsObj);
                    int newLength = Array.getLength(newDexElementsObj);
                    Object concatDexElementsObject = Array.newInstance(dexElementsObj.getClass().getComponentType(), oldLength + newLength);
                    for (int i = 0; i < newLength; i++) {
                        Array.set(concatDexElementsObject, i, Array.get(newDexElementsObj, i));
                    }
                    for (int i = 0; i < oldLength; i++) {
                        Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObj, i));
                    }
                    dexElementsField.set(pathListObj, newDexElementsObj);
                    SaySuccess saySuccessHot = new SaySuccess();
                    mBinding.showTv.setText(saySuccessHot.say());
                } catch (NoSuchFieldException
                        | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void getFieldValue() {


        try {
            Class<?> aClass = Class.forName("com.zqg.hotfixdemo.DataBean");
            Object dataBean = aClass.newInstance();
            Field title = aClass.getDeclaredField("otherBean");
            title.setAccessible(true);
            Object oterBeanObj = title.get(dataBean);
            Class<?> otherBeanClass = oterBeanObj.getClass();
            Field content = otherBeanClass.getDeclaredField("content");
            content.setAccessible(true);
            Object contentObj = content.get(oterBeanObj);
            Log.d(TAG, contentObj.toString());
            content.set(oterBeanObj, "值被改变了");
            contentObj = content.get(oterBeanObj);
            Log.d(TAG, contentObj.toString());
        } catch (ClassNotFoundException | NoSuchFieldException
                | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
