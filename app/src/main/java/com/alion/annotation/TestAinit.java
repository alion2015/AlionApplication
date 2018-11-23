package com.alion.annotation;


public class TestAinit
{
    private String name;
    private String age;

    public String getName()
    {
        return name;
    }

    @Ainit(hshs = "alion")
    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    @Ainit("53") //默认设置给value
    public void setAge(String age)
    {
        this.age = age;
    }
}
