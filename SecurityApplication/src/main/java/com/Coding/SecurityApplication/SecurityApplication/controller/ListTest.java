package com.Coding.SecurityApplication.SecurityApplication.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> ab = new ArrayList<String>();

        ab.add("manish");
        ab.add("kumar");

//        for(String c : ab) {
//            System.out.println(c);
//        }

        Iterator t = ab.iterator();

        while(t.hasNext()) {
            System.out.println(t.next());
        }

    }

}
