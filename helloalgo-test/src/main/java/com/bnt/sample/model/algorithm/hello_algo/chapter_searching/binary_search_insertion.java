package com.bnt.sample.model.algorithm.hello_algo.chapter_searching;

/**
 * 二分查找插入点
 * Question
 * 给定一个长度为 n 的有序数组 nums 和一个元素 target ，数组不存在重复元素。
 * 现将 target 插入数组 nums 中，并保持其有序性。
 * 若数组中已存在元素 target ，则插入到其左方。请返回插入后 target 在数组中的索引。
 * <p>
 * <p>
 * <p>
 * <p>
 * 为什么i一定在大于等于target的位置
 * ● i只有m中值小于target才会m+1
 * if (nums[m] < target) {
 * i = m + 1;
 * 所以只要i小于target，它就会一直m加一，直到位于大于等于target的位置，甚至超出数组（可以推理一下target大于数组全部的数组情况，可以帮助理解）。
 */
public class binary_search_insertion {
    /* 二分查找插入点（无重复元素） */
    static int binarySearchInsertionSimple(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1]
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            // 计算中点索引 m
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                // target 在区间 [m+1, j] 中
                i = m + 1;
            } else if (nums[m] > target) {
                // target 在区间 [i, m-1] 中
                j = m - 1;
            } else {
                // 找到 target ，返回插入点 m
                return m;
            }
        }
        // 未找到 target ，返回插入点 i
        return i;
    }

    /* 二分查找插入点（存在重复元素） */
    static int binarySearchInsertion(int[] nums, int target) {
        // 初始化双闭区间[0，n-1]
        int i = 0, j = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当i>j）时为空
        while (i <= j) {
            // 计算中点索引m
            int m = (i + j) / 2;
            if (nums[m] < target) {
                // target在区间[m+1,j]中
                i = m + 1;
            } else if (nums[m] > target) {
                // target 在区间 [i, m-1] 中
                j = m - 1;
            } else {
                // 首个小于 target 的元素在区间 [i, m-1] 中
                j = m - 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        // 无重复元素的数组
        int[] nums = {1, 3, 6, 8, 12, 15, 23, 26, 31, 35};
        System.out.println("\n数组 nums = " + java.util.Arrays.toString(nums));
        // 二分查找插入点
        for (int target : new int[]{6, 9}) {
            int index = binarySearchInsertionSimple(nums, target);
            System.out.println("元素 " + target + " 的插入点的索引为 " + index);
        }

        // 包含重复元素的数组
        nums = new int[]{1, 3, 6, 6, 6, 6, 6, 10, 12, 15};
        System.out.println("\n数组 nums = " + java.util.Arrays.toString(nums));
        // 二分查找插入点
        for (int target : new int[]{2, 6, 20}) {
            int index = binarySearchInsertion(nums, target);
            System.out.println("元素 " + target + " 的插入点的索引为 " + index);
        }
    }
}
