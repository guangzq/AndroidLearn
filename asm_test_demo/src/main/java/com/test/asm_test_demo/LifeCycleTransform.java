package com.test.asm_test_demo;


import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/4/13 14:24
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class LifeCycleTransform extends Transform {


    @Override
    public String getName() {
        return "zqgLifeCycle";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation)
            throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        System.out.println("zqg=====transform");
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        if (outputProvider != null) {
            outputProvider.deleteAll();
        }
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        inputs.forEach(new Consumer<TransformInput>() {
            @Override
            public void accept(TransformInput transformInput) {
                transformInput.getDirectoryInputs().forEach(new Consumer<DirectoryInput>() {
                    @Override
                    public void accept(DirectoryInput directoryInput) {
                        File contentLocation = outputProvider.getContentLocation(directoryInput.getName(),
                                directoryInput.getContentTypes(), directoryInput.getScopes(),
                                Format.DIRECTORY);
                        File file = directoryInput.getFile();
                        findFile(file, contentLocation);
                        try {
                            FileUtils.copyDirectory(directoryInput.getFile(), contentLocation);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                transformInput.getJarInputs().forEach(new Consumer<JarInput>() {
                    @Override
                    public void accept(JarInput jarInput) {
                        File contentLocation = outputProvider.getContentLocation(jarInput.getName(),
                                jarInput.getContentTypes(), jarInput.getScopes(),
                                Format.JAR);
                        try {
                            FileUtils.copyFile(jarInput.getFile(), contentLocation);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void findFile(File file, File toFile) {
        if (file != null && file.exists()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File file1 : files) {
                if (file1 == null || !file1.exists()) return;
                if (file1.isFile()) {
                    System.out.println("zqg=====find class==" + file1.getName());
                    byte[] bytes = new byte[0];
                    try {
                        bytes = Files.readAllBytes(file1.toPath());
                        ClassReader classReader = new ClassReader(bytes);
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                        ClassVisitor myClassVistor = new MyClassVistor(classWriter);
                        classReader.accept(myClassVistor, ClassReader.EXPAND_FRAMES);
                        byte[] bytes1 = classWriter.toByteArray();
                        FileOutputStream fileOutputStream = new FileOutputStream(file1.getAbsolutePath());
                        fileOutputStream.write(bytes1);
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    findFile(file1, toFile);
                }
            }
        }
    }
}
