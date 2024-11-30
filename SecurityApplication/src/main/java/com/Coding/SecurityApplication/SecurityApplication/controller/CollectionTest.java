package com.Coding.SecurityApplication.SecurityApplication.controller;

class GenericType<T> {

    private T item;
    public void setItem(T item) {
        this.item = item;
    }
    public T getItem() {
        return this.item;
    }
}

class HashMapGeneric<K,V> {
    private K key;
    private V value;

    public HashMapGeneric(K key,V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value of hashmap: Key -> " + this.key + " Value -> " + this.value;
    }
}
public class CollectionTest {

    private static <T,K> boolean checkEquality(T item1,K item2) {
        return item1.equals(item2);
    }

    public static void main(String[] args) {

        GenericType<String> a = new GenericType<>();
        a.setItem("Manish Kumar");
        System.out.println(a.getItem());

        GenericType<Integer> b = new GenericType<>();
        b.setItem(10);
        System.out.println(b.getItem());

        HashMapGeneric<String,Integer> c = new HashMapGeneric<>("M",10);
        System.out.println(c);

        System.out.println("Equal or not --" + checkEquality(10,12));
        System.out.println("Equal or not --" + checkEquality(1000,1000));
        System.out.println("Equal or not --" + checkEquality(10,10));
        System.out.println("Equal or not --" + checkEquality("manish",10));
        System.out.println("Equal or not --" + checkEquality("manish","manish"));

    }
}
