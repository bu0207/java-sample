package com.bnt.sample.model.algorithm.hello_algo.chapter_searching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 哈希优化策略
 * <p>
 * question
 * 给定一个整数数组 nums 和一个目标元素 target ，
 * 请在数组中搜索“和”为 target 的两个元素，并返回它们的数组索引。返回任意一个解即可。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/1 16:17 bnt
 * @history
 */
public class two_sum {

    /* 方法一：暴力枚举 */
    static int[] twoSumBruteForce(int[] nums, int target) {
        // 两层循环，时间复杂度为 O(n^2)
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /* 方法二：辅助哈希表 */
    static int[] twoSumHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        // ======= Test Case =======
        int[] nums = {2, 7, 11, 15};
        int target = 13;

        // ====== Driver Code ======
        // 方法一
        int[] res = twoSumBruteForce(nums, target);
        System.out.println("方法一 res = " + Arrays.toString(res));
        // 方法二
        res = twoSumHashTable(nums, target);
        System.out.println("方法二 res = " + Arrays.toString(res));
    }
}
