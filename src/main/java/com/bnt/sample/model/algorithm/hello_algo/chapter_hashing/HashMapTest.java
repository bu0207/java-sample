package com.bnt.sample.model.algorithm.hello_algo.chapter_hashing;

import com.bnt.sample.model.algorithm.hello_algo.utils.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希表常用操作
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/5 17:38 bnt
 * @history
 */
public class HashMapTest {
    public static void main(String[] args) {
        /* 初始化哈希表 */
        Map<Integer, String> map = new HashMap<>();

        /* 添加操作 */
        // 在哈希表中添加键值对 (key, value)
        map.put(12836, "小哈");
        map.put(15937, "小啰");
        map.put(16750, "小算");
        map.put(13276, "小法");
        map.put(10583, "小鸭");
        System.out.println("\n添加完成后，哈希表为\nKey -> Value");

        PrintUtil.printHashMap(map);

        String name = map.get(15937);
        System.out.println("\n输入学号 15937 ，查询到姓名 " + name);
        /* 删除操作 */
        // 在哈希表中删除键值对 (key, value)
        map.remove(10583);
        System.out.println("\n删除 10583 后，哈希表为\nKey -> Value");
        PrintUtil.printHashMap(map);
        /* 遍历哈希表 */
        System.out.println("\n遍历键值对 Key->Value");
        for (Map.Entry<Integer, String> kv : map.entrySet()) {
            System.out.println(kv.getKey() + " -> " + kv.getValue());
        }
        System.out.println("\n单独遍历键 Key");
        for (Integer key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println("\n单独遍历值 Value");
        for (String value : map.values()) {
            System.out.println(value);
        }


    }
}
