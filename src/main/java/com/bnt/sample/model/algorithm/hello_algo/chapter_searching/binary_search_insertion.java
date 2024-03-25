package com.bnt.sample.model.algorithm.hello_algo.chapter_searching;

/**
 * 二分查找插入点
 * Question
 * 给定一个长度为 n 的有序数组 nums 和一个元素 target ，数组不存在重复元素。
 * 现将 target 插入数组 nums 中，并保持其有序性。
 * 若数组中已存在元素 target ，则插入到其左方。请返回插入后 target 在数组中的索引。
 */
public class binary_search_insertion {
    /* 二分查找插入点（无重复元素） */
    static int binarySearchInsertionSimple(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                i = m + 1;
            } else if (nums[m] > target) {
                j = m - 1;
            } else {
                return m;
            }
        }
        return i;
    }
}
