package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 工作原理与手动整理一副牌的过程非常相似。
 * 我们在未排序区间选择一个基准元素，将该元素与其左侧已排序区间的元素逐一比较大小，
 * 并将该元素插入到正确的位置。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/2 14:43 bnt
 * @history
 */
public class insertion_sort {
    /* 插入排序 */
    static void insertionSort(int[] nums) {
        // 外循环：已排序区间为 [0, i-1]
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i];
            int j = i - 1;
            // 内循环：将 base 插入到已排序区间 [0, i-1] 中的正确位置
            while (j >= 0 && nums[j] > base) {
                // 将 nums[j] 向右移动一位
                nums[j + 1] = nums[j];
                j--;
            }
            // 将 base 赋值到正确位置
            nums[j + 1] = base;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 3, 1, 5, 2};
        insertionSort(nums);
        System.out.println("插入排序完成后 nums = " + Arrays.toString(nums));
    }
}
