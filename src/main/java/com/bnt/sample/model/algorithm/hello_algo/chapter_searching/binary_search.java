package com.bnt.sample.model.algorithm.hello_algo.chapter_searching;

/**
 * 二分查找
 * -----
 * Question
 * 给定一个长度为 n 的数组 nums ，元素按从小到大的顺序排列且不重复。
 * 请查找并返回元素 target 在该数组中的索引。若数组不包含该元素，则返回 −1 。
 */
public class binary_search {

    /* 二分查找（双闭区间） */
    static int binarySearch(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 i, j 分别指向数组首元素、尾元素
        int i = 0, j = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 i > j 时为空）
        while (i <= j) {
            // 计算中点索引 m
            int m = (i + j) / 2;
            if (nums[m] > target) {// 此情况说明 target 在区间 [i, m-1] 中
                j = m - 1;
            } else if (nums[m] < target) {// 此情况说明 target 在区间 [m+1, j] 中
                i = m + 1;
            } else {
                // 找到目标元素，返回其索引
                return m;
            }
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

    /* 二分查找（左闭右开区间） */
    static int binarySearchLCRO(int[] nums, int target) {
        int i = 0, j = nums.length;
        // 循环，当搜索区间为空时跳出（当 i = j 时为空）
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] > target) {// 此情况说明 target 在区间 [i, m) 中
                j = m;
            } else if (nums[m] < target) {// 此情况说明 target 在区间 [m+1, j) 中
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int target = 6;
        int[] nums = {1, 3, 6, 8, 12, 15, 23, 26, 31, 35, 100, 200, 300};

        /* 二分查找（双闭区间） */
        int index = binarySearch(nums, target);
        System.out.println("目标元素 6 的索引 = " + index);

        /* 二分查找（左闭右开区间） */
        index = binarySearchLCRO(nums, target);
        System.out.println("目标元素 6 的索引 = " + index);
    }

}
