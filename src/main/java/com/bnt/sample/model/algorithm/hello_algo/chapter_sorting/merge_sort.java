package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * <p>
 * <p>
 * 归并排序 merge sort」是一种基于分治策略的排序算法，包含“划分”和“合并”阶段。
 * 1. 划分阶段：通过递归不断地将数组从中点处分开，将长数组的排序问题转换为短数组的排序问题。
 * 2. 合并阶段：当子数组长度为 1 时终止划分，开始合并，持续地将左右两个较短的有序数组合并为一个较长的有序数组，直至结束。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/7 14:59 bnt
 * @history
 */
public class merge_sort {

    /* 合并左子数组和右子数组 */
    static void merge(int[] nums, int left, int mid, int right) {
        // 初始化左子数组和右子数组的起始索引
        int i = left, j = mid + 1, k = 0;
        // 左子数组区间为 [left, mid], 右子数组区间为 [mid+1, right]
        // 创建一个临时数组 tmp ，用于存放合并后的结果
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        // 将左子数组和右子数组的剩余元素复制到临时数组中
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        // 将临时数组 tmp 中的元素复制回原数组 nums 的对应区间
        for (int m = 0; m < tmp.length; m++) {
            nums[left + m] = tmp[m];
        }
    }

    /* 归并排序 */
    static void mergeSort(int[] nums, int left, int right) {
        // 终止条件
        if (left >= right) {
            return;
        }
        // 划分阶段
        int mid = (left + right) / 2;// 计算中点
        // 递归左子数组
        mergeSort(nums, left, mid);
        // 递归右子数组
        mergeSort(nums, mid + 1, right);
        // 合并阶段
        merge(nums, left, mid, right);
    }

    public static void main(String[] args) {
        /* 归并排序 */
        int[] nums = {7, 3, 2, 6, 0, 1, 5, 4};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println("归并排序完成后 nums = " + Arrays.toString(nums));
    }
}
