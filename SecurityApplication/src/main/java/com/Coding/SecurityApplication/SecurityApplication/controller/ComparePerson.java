package com.Coding.SecurityApplication.SecurityApplication.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Person implements Comparable<Person> {

    private int age;
    private String name;

    public  Person(int age,String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(age,o.age);
    }

    @Override
    public String toString() {
        return "age --" + this.getAge() + " name --" + this.getName();
    }
}

public class ComparePerson {

    public static void main(String[] args) {


        List<Integer> abc = List.of(1,2,3,4,5);
        System.out.println(abc);

        Person a = new Person(12,"Manish");
        Person b = new Person(0,"Mayur");
        Person c = new Person(24,"Taran");
        Person d = new Person(6,"Tushar");

//        List<Person> resp = List.of(a,b,c,d);

        ArrayList<Person> resp
                = new ArrayList<>();

        resp.add(a);
        resp.add(b);
        resp.add(c);
        resp.add(d);

        System.out.println(resp);
        Collections.sort(resp);
        System.out.println(resp);

    }


}
