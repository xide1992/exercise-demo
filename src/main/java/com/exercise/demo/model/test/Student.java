package com.exercise.demo.model.test;

/**
 * @Author: xdz
 * @Descrption:  fluent风格
 * @Date: 2019/9/17 15:04
 */
public class Student {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", address=" + address + "]";
    }


    public static void main(String[] args) {
        Student student = new Student();
        student.setName("张三");
        student.setAddress("广东深圳");
        System.out.println(student);
    }
}
