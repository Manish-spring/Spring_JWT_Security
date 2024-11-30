package com.Coding.SecurityApplication.SecurityApplication.controller;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringTest {

    public static void main(String[] args) {

        List<Integer> ab = Arrays.asList(12,23,34,45);

        Integer count = ab.stream().mapToInt(Integer::intValue).sum();
        System.out.println(count);
        //List<Employee> empList = getEmpList();

//        List<Employee> resp = empList.stream()
//                //.filter(e -> e.getGender().equals("Female") && e.getNewJoiner().equals("TRUE"))
//                .filter(e -> e.getRating() > 2)
////                .allMatch(e -> e.getRating() > 2)
//                //.sorted(Comparator.comparing(Employee::getRating))
//                .toList();

//        System.out.println(resp.size());
//
//        for(Employee p : resp) {
//            System.out.println(p.getEmpId() + " -- " + p.getFirstName() + " -- " + p.getRating());
//        }

    }

    public static List<Employee> getEmpList(){
        return Arrays.asList(
                new Employee("59-385-1088","Zacharias","Schwerin","zchwerin@gmail.com","Male","True",101146,0),
                new Employee("73-274-6476","Kyle","Frudd","kfrudd1@ovh.net","Male","FALSE",29310,2),
                new Employee("85-939-9584","Axe","Gumb","agumb2@twitter.com","Female","TRUE",62291,4),
                new Employee("08-180-8292","Robinet","Batterham","rbatterham3@last.fm","Male","FALSE",142439,4),
                new Employee("21-825-2623","Ulick","Burrel","uburrel4@google.ru","Male","FALSE",128764,5),
                new Employee("66-708-5539","Tailor","Ridding","Ridding","Female","FALSE",152924,4),
                new Employee("81-697-2363","Joete","Braybrooke","jbraybrooke6@prnewswire.com","Male","TRUE",128907,0),
                new Employee("63-019-1110","Elroy","Baverstock","ebaverstock7@ehow.com","Male","TRUE",2510,0)
        );
    }
}

