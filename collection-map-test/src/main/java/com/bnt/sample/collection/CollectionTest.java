package com.bnt.sample.collection;

import java.util.HashSet;
import java.util.Iterator;

public class CollectionTest {
    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        hashSet.add("d");
        Iterator<Object> iterator = hashSet.iterator();
        for (int i = 0; i < hashSet.size(); i++) {
            System.out.println(iterator.next());
        }
    }
}
