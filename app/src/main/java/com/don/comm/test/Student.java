package com.don.comm.test;

/**
 * FileName: Student
 * Author: SnowDragon
 * Date: 2020/12/24 8:21
 * Description: public
 */
public class Student {
    public String name;
    public int age;


    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }
}
