package com.exercise.demo.model.test;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/17 15:05
 */
public class StudentFluent {

    private String name;
    private String address;

    // 设置name的值，并且返回实体
    public StudentFluent setName(String name){
        this.name = name;
        return this;
    }

    // 设置address的值，并且返回实体
    public StudentFluent setAddress(String address){
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }

    // 返回学生实体，可以做成单例
    public static StudentFluent build(){
        return new StudentFluent();
    }

    @Override
    public String toString() {
        return "StudentFluent [name=" + name + ", address=" + address + "]";
    }


    public static void main(String[] args) {
        StudentFluent student = StudentFluent.build().setName("李四").setAddress("广东广州");
        System.out.println(student.toString());
    }
}
