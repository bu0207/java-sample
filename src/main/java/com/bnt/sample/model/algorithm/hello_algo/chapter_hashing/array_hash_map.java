package com.bnt.sample.model.algorithm.hello_algo.chapter_hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * 哈希表简单实现
 *
 * @author bnt
 * @version 1.0.0
 */
class Pair {
    public int key;
    public String val;

    public Pair(int key, String val) {
        this.key = key;
        this.val = val;
    }

}

/* 基于数组实现的哈希表 */
class ArrayHashMap {
    private List<Pair> buckets;

    // 初始化数组，包含 100 个桶
    public ArrayHashMap() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            buckets.add(null);
        }
    }

    /* 哈希函数 */
    public int hashFunc(int key) {
        return key % 100;
    }

    /* 查询操作 */
    public String get(int key) {
        int i = hashFunc(key);
        Pair pair = buckets.get(i);
        if (pair != null) {
            return pair.val;
        }
        return null;
    }

    /* 添加操作 */
    public void put(int key, String val) {
        Pair pair = new Pair(key, val);
        int i = hashFunc(key);
        buckets.set(i, pair);
    }

    /* 删除操作 */
    public void remove(int key) {
        int i = hashFunc(key);
        buckets.set(i, null);
    }

    /* 获取所有键值对 */
    public List<Pair> pairSet() {
        List<Pair> list = new ArrayList<>();
        for (Pair bucket : buckets) {
            if (bucket != null) {
                list.add(bucket);
            }
        }
        return list;
    }

    /* 获取所有键 */
    public List<Integer> keySet() {
        List<Integer> list = new ArrayList<>();
        for (Pair bucket : buckets) {
            if (bucket != null) {
                list.add(bucket.key);
            }
        }
        return list;
    }

    /* 获取所有值 */
    public List<String> valueSet() {
        List<String> list = new ArrayList<>();
        for (Pair bucket : buckets) {
            if (bucket != null) {
                list.add(bucket.val);
            }
        }
        return list;
    }

    /* 打印哈希表 */
    public void print() {
        for (Pair kv : pairSet()) {
            System.out.println(kv.key + " -> " + kv.val);
        }
    }
}

public class array_hash_map {
    public static void main(String[] args) {
        /* 初始化哈希表 */
        ArrayHashMap map = new ArrayHashMap();

        /* 添加操作 */
        // 在哈希表中添加键值对 (key, value)
        map.put(12836, "小哈");
        map.put(15937, "小啰");
        map.put(16750, "小算");
        map.put(13276, "小法");
        map.put(10583, "小鸭");
        System.out.println("\n添加完成后，哈希表为\nKey -> Value");
        map.print();

        /* 查询操作 */
        // 向哈希表中输入键 key ，得到值 value
        String name = map.get(15937);
        System.out.println("\n输入学号 15937 ，查询到姓名 " + name);

        /* 删除操作 */
        // 在哈希表中删除键值对 (key, value)
        map.remove(10583);
        System.out.println("\n删除 10583 后，哈希表为\nKey -> Value");
        map.print();

        /* 遍历哈希表 */
        System.out.println("\n遍历键值对 Key->Value");
        for (Pair kv : map.pairSet()) {
            System.out.println(kv.key + " -> " + kv.val);
        }
        System.out.println("\n单独遍历键 Key");
        for (int key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println("\n单独遍历值 Value");
        for (String val : map.valueSet()) {
            System.out.println(val);
        }
    }
}
