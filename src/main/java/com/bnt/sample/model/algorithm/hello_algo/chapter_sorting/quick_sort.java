package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

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

    }

    /* 快速排序 */
}


public class quick_sort {


}
