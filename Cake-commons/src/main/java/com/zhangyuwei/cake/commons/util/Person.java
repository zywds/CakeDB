package com.zhangyuwei.cake.commons.util;

public class Person {
    private String no;
    private String name;
    private int age;
    public Person(){

    }
    public Person(String no,String name,int age){
        this.no=no;
        this.name=name;
        this.age=age;
    }
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "sortUtils{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
