package com.bnt.sample.model.algorithm.hello_algo.chapter_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 链式地址哈希表
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/6 11:30 bnt
 * @history
 */
class HashMapChaining {
    // 键值对数量
    int size;

    // 哈希表容量
    int capacity;

    // 触发扩容的负载因子阈值
    double loadThres;

    // 扩容倍数
    int extendRatio;

    List<List<Pair>> buckets;

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThres = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    /* 哈希函数 */
    public int hashFunc(int key) {
        return key % capacity;
    }

    /* 负载因子 */
    public double loadFactor() {
        return (double) size / capacity;
    }

    /* 查询操作 */
    public String get(int key) {
        int i = hashFunc(key);
        List<Pair> pairs = buckets.get(i);
        if (pairs != null) {
            for (Pair pair : pairs) {
                if (pair.key == key) {
                    return pair.val;
                }
            }
        }
        return null;
    }

    /* 添加操作 */
    public void put(int key, String val) {
        // 当负载因子超过阈值，执行扩容
        if (loadFactor() > loadThres) {
            extend();
        }
        int i = hashFunc(key);
        // 遍历桶，若遇到指定 key ，则更新对应 val 并返回
        List<Pair> bucket = buckets.get(i);
        for (Pair pair : bucket) {
            if (key == pair.key) {
                pair.val = val;
                return;
            }
        }
        // 若无该 key ，则将键值对添加至尾部
        bucket.add(new Pair(key, val));
        size++;
    }

    /* 删除操作 */
    public void remove(int key) {
        int i = hashFunc(key);
        List<Pair> pairs = buckets.get(i);
        for (int j = 0; j < pairs.size(); j++) {
            if (pairs.get(j).key == key) {
                pairs.remove(j);
                size--;
                break;
            }
        }
    }

    /* 扩容哈希表 */
    public void extend() {
        System.out.println("开始扩容");
        List<List<Pair>> bucketsTmp = buckets;
        // 初始化扩容后的新哈希表
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
        for (List<Pair> pairs : bucketsTmp) {
            for (Pair pair : pairs) {
                put(pair.key, pair.val);
            }
        }
    }

    /* 打印哈希表 */
    public void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + "->" + pair.val);
            }
            System.out.println(res);
        }
    }


}

public class hash_map_chaining {
    public static void main(String[] args) {
        /* 初始化哈希表 */
        HashMapChaining map = new HashMapChaining();

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
        String name = map.get(13276);
        System.out.println("\n输入学号 13276 ，查询到姓名 " + name);

        /* 删除操作 */
        // 在哈希表中删除键值对 (key, value)
        map.remove(12836);
        System.out.println("\n删除 12836 后，哈希表为\nKey -> Value");
        map.print();
    }
}
