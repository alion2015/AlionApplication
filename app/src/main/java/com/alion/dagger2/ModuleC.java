package com.alion.dagger2;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * 数据工厂类
 * Provides按照返回值类型进行匹配，可以用@Named@Qualifier来限制
 * Provides与@Inject修饰的构造器等效
 */
@Module
public class ModuleC {
    @Provides
    ClassD provideD(){
        return new ClassD();
    }
}
