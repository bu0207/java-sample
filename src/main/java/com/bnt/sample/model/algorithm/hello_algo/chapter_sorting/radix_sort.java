package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

/**
 * 基数排序
 * <p>
 * 核心思想与计数排序一致，也通过统计个数来实现排序。
 * 在此基础上，基数排序利用数字各位之间的递进关系，依次对每一位进行排序，从而得到最终的排序结果。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/11 17:50 bnt
 * @history
 */
public class radix_sort {
    /*获取元素num的第k位,其中 exp = 10^(k-1) */
    static int digit(int num, int exp) {
        return (num / exp) % 10;
    }

    /* 计数排序（根据 nums 第 k 位排序） */
    static void countingSortDigit(int[] nums, int exp) {
        int[] counter = new int[10];
        for (int num : nums) {
            int digit = digit(num,exp);

        }
    }

    /* 基数排序 */
    static void radixSort(int[] nums) {

    }
}
