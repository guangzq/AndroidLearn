package com.test.asm_test_demo;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/4/13 10:53
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class AsmTest implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("====zqg test====");
        AppExtension byType = project.getExtensions().getByType(AppExtension.class);
        System.out.println("=====register auto=====");
        byType.registerTransform(new LifeCycleTransform());
    }
}
