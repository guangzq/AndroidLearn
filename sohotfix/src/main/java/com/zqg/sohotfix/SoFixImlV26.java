package com.zqg.sohotfix;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2021/1/22 15:48
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class SoFixImlV26 implements ISoFix {
    private Context mContext;
    public SoFixImlV26(Context mContext) {
        mContext = mContext;

    }

    @Override
    public void injectSo(String soDir) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        ClassLoader classLoader = mContext.getClassLoader();
        Object pathList = ReflectUtil.getFiledValue(classLoader, "pathList");
        Field nativeLibraryPathElements = ReflectUtil.getFiled(pathList, "nativeLibraryPathElements");
        Object nativeLibraryValue = nativeLibraryPathElements.get(pathList);
        Class<?> nativeLibraryElementClass = nativeLibraryValue.getClass().getComponentType();
        Constructor<?> elementClassConstructor = nativeLibraryElementClass.getConstructor(File.class);
        elementClassConstructor.setAccessible(true);
        Object element = elementClassConstructor.newInstance(new File(soDir));
        Object newElements = CommonUtils.insertElementAtFirst(element, nativeLibraryValue);
        nativeLibraryPathElements.set(pathList, newElements);
    }
}
