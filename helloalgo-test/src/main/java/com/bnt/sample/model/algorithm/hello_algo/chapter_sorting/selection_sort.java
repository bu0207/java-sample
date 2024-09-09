package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;

/**
 * 选择排序
 * <p>
 * 开启一个循环，每轮从未排序区间选择最小的元素，将其放到已排序区间的末尾。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/2 10:49 bnt
 * @history
 */
public class selection_sort {
    /* 选择排序 */
    public static void selectionSort(int[] nums) {
        // 外循环：未排序区间为 [i, n-1]
        for (int i = 0; i < nums.length - 1; i++) {
            // 内循环：找到未排序区间内的最小元素
            int m = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[m]) {
                    // 记录最小元素的索引
                    m = j;
                }
            }
            // 将该最小元素与未排序区间的首个元素交换
            int tmp = nums[i];
            nums[i] = nums[m];
            nums[m] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 4, 1, 3, 1, 5, 2 };
        selectionSort(nums);
        System.out.println("选择排序完成后 nums = " + Arrays.toString(nums));
    }
}
