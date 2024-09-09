package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;

/**
 * 冒泡排序
 * <p>
 * 通过连续地比较与交换相邻元素实现排序
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/2 10:39 bnt
 * @history
 */
public class bubble_sort {
    /* 冒泡排序 */
    static void bubbleSort(int[] nums) {
        // 外循环，未排序区间为 [0, i]
        for (int i = nums.length - 1; i > 0; i--) {
            // 内循环，将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 交换 nums[j] 与 nums[j + 1]
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    /* 冒泡排序（标志优化） */
    static void bubbleSortWithFlag(int[] nums) {
        // 外循环：未排序区间为 [0, i]
        for (int i = nums.length - 1; i > 0; i--) {
            // 初始化标志位
            boolean flag = false;
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 交换 nums[j] 与 nums[j + 1]
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = true;
                }
            }
            //  此轮“冒泡”未交换任何元素，直接跳出
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 4, 1, 3, 1, 5, 2 };
        bubbleSort(nums);
        System.out.println("冒泡排序完成后 nums = " + Arrays.toString(nums));

        int[] nums1 = { 4, 1, 3, 1, 5, 2 };
        bubbleSortWithFlag(nums1);
        System.out.println("冒泡排序完成后 nums1 = " + Arrays.toString(nums1));
    }
}
