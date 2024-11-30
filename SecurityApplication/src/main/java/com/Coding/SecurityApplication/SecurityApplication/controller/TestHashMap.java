package com.Coding.SecurityApplication.SecurityApplication.controller;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.*;

public class TestHashMap {

    public static void main(String[] args) {

        HashMap<String, String> hm = new HashMap<>();


        String a = "abcccc";
        String b = new String("abcccc");

        System.out.println(a == b);

        System.out.println("hash code a ===" + a.hashCode());
        System.out.println("hash code b ===" + b.hashCode());
        hm.put(a,"manish");
        hm.put(b,"kumar");
        System.out.println(hm);

    }

}
