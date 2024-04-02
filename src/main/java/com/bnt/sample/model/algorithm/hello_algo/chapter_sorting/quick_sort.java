package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;
/**
 * 快速排序
 * <p>
 * 「快速排序 quick sort」是一种基于分治策略的排序算法。
 * 快速排序的核心操作是“哨兵划分”，其目标是：选择数组中的某个元素作为“基准数”，
 * 将所有小于基准数的元素移到其左侧，而大于基准数的元素移到其右侧。
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/2 17:23 bnt
 * @history
 */
class QuickSort {
    /*交换元素*/
    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 哨兵划分 */
    static int partition(int[] nums, int left, int right) {
        // 以 nums[left] 为基准数
        int i = left, j = right;
        while (i < j) {
            // 从右向左找首个小于基准数的元素
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            // 从左向右找首个大于基准数的元素
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            // 交换这两个元素
            swap(nums, i, j);
        }
        // 将基准数交换至两子数组的分界线
        swap(nums, i, left);
        return i;
    }

    /* 快速排序 */

    /**
     * 1. 首先，对原数组执行一次“哨兵划分”，得到未排序的左子数组和右子数组。
     * 2. 然后，对左子数组和右子数组分别递归执行“哨兵划分”。
     * 3. 持续递归，直至子数组长度为 1 时终止，从而完成整个数组的排序。
     *
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSort(int[] nums, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right) {
            return;
        }
        // 哨兵划分
        int i = partition(nums, left, right);
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }
}


public class quick_sort {
    public static void main(String[] args) {
        /* 快速排序 */
        int[] nums = {2, 4, 1, 0, 3, 5};
        QuickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println("快速排序完成后 nums = " + Arrays.toString(nums));

//        /* 快速排序（中位基准数优化） */
//        int[] nums1 = {2, 4, 1, 0, 3, 5};
//        QuickSortMedian.quickSort(nums1, 0, nums1.length - 1);
//        System.out.println("快速排序（中位基准数优化）完成后 nums1 = " + Arrays.toString(nums1));
//
//        /* 快速排序（尾递归优化） */
//        int[] nums2 = {2, 4, 1, 0, 3, 5};
//        QuickSortTailCall.quickSort(nums2, 0, nums2.length - 1);
//        System.out.println("快速排序（尾递归优化）完成后 nums2 = " + Arrays.toString(nums2));
    }

}
