package com.test.asm_test_demo;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/4/13 16:05
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class MyMeth extends MethodVisitor {

    private String mMethodName;
    private String mClassName;

    public MyMeth(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    public MyMeth(MethodVisitor visitor, String className, String methodName) {
        super(Opcodes.ASM5, visitor);
        this.mClassName = className;
        this.mMethodName = methodName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("visitCode="
                + "methodName:" + mMethodName + ";className:" + mClassName);
        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn(mClassName + "=====" + mMethodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }
}
