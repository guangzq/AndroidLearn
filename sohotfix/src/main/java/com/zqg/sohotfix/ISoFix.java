package com.zqg.sohotfix;

import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2021/1/22 15:50
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
interface ISoFix {
    void injectSo(String soDir) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException;
}
