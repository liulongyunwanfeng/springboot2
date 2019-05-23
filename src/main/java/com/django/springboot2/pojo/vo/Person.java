package com.django.springboot2.pojo.vo;

/**
 * @ClassName Person
 * @Author Administrator
 * @Date 2019/5/10
 * @Version 1.0
 * @Description TODO
 */
public class Person {
    private String name;
    private Integer age;

    public Person() {
        super();
    }

    public Person(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
