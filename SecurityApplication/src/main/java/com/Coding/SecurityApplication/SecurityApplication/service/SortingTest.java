package com.Coding.SecurityApplication.SecurityApplication.service;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.*;

public class SortingTest {

    public static void main(String[] args) {

        List<Student> student = new ArrayList<>();

        student.add(new Student(10,"Manish"));
        student.add(new Student(5,"Kumar"));
        student.add(new Student(-10,"Singh"));
        student.add(new Student(1,"Cool"));

        Collections.sort(student);

        for(Student s : student) {
            System.out.println(s);
        }

    }
}
