package com.alion.dagger2;

import dagger.Component;

/**
 * 核心入口：每一个modules或dependencies都会成为生成代码的field
 * 接口中函数有返回值的可为其他Component的dependencies的数据源
 *            传入参数表示含有Inject修饰的变量
 * modules指向工厂类
 */
@Component(modules = ModuleC.class)
public interface BComponent {
    //ClassA provideA();
    void inject(ClassB b);
}
