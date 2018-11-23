package com.alion.annotation;

import java.lang.reflect.Method;

public class UserFactory
{
    public static TestAinit create()
    {
        TestAinit TestAinit = new TestAinit();

        // 获取TestAinit类中所有的方法（getDeclaredMethods也行）
        Method[] methods = TestAinit.class.getMethods();

        try
        {
            for (Method method : methods)
            {
                // 如果此方法有注解，就把注解里面的数据赋值到TestAinit对象
                if (method.isAnnotationPresent(Ainit.class))
                {
                    Ainit init = method.getAnnotation(Ainit.class);
                    method.invoke(TestAinit, init.hshs());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return TestAinit;
    }
}