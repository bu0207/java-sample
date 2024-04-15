package com.bnt.sample.model.algorithm.hello_algo.chapter_divide_and_conquer;

/**
 * 二分查找：问题 f(i, j)
 * <p>
 * 给定一个长度为 n 的有序数组 nums ，其中所有元素都是唯一的，请查找元素 target 。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/15 16:43 bnt
 * @history
 */
public class binary_search_recur {
    /* 二分查找：问题 f(i, j) */
    static int dfs(int[] nums, int target, int i, int j) {
        // 若区间为空，代表无目标元素，则返回 -1
        if (i > j) {
            return -1;
        }
        // 计算中点索引 m
        int m = (i + j) / 2;
        if (nums[m] < target) {
            // 递归子问题 f(m+1, j)
            return dfs(nums, target, m + 1, j);
        } else if (nums[m] > target) {
            // 递归子问题 f(i, m-1)
            return dfs(nums, target, i, m - 1);
        } else {
            // 找到目标元素，返回其索引
            return m;
        }
    }

    /* 二分查找 */
    static int binarySearch(int[] nums, int target) {
        int n = nums.length;
        // 求解问题 f(0, n-1)
        return dfs(nums, target, 0, n - 1);
    }

    public static void main(String[] args) {
        int target = 6;
        int[] nums = {1, 3, 6, 8, 12, 15, 23, 26, 31, 35};

        // 二分查找（双闭区间）
        int index = binarySearch(nums, target);
        System.out.println("目标元素 6 的索引 = " + index);
    }
}
