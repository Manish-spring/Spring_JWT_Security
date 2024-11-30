package com.Coding.SecurityApplication.SecurityApplication.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student implements Comparable<Student>{

    private int age;
    private String name;

    public Student(int age,String name) {
        this.age = age;
        this.name = name;
    }
    @Override
    public int compareTo(Student other) {
        return Integer.compare(other.age,this.age);
    }

    @Override
    public String toString() {
        return "[" + this.name + " -- " + this.age + "]";
    }
}
