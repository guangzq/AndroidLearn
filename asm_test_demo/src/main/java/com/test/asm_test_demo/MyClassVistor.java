package com.test.asm_test_demo;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/4/13 15:50
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class MyClassVistor extends ClassVisitor {
    private String mName;
    private String mSuperName;

    public MyClassVistor(int api) {
        super(api);
    }

    public MyClassVistor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.mName = name;
        this.mSuperName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
        System.out.println("visitMethod="
                + "methodName:" + name + ";className:" + mName);
        if (mSuperName.equals("androidx/appcompat/app/AppCompatActivity")) {
            if (name.startsWith("onCreate")) {
                return new MyMeth(methodVisitor, mName, name);
            }
        }
        return methodVisitor;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
