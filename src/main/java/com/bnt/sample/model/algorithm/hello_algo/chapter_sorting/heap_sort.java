package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/8 14:14 bnt
 * @history
 */
public class heap_sort {

    /* 堆的长度为 n ，从节点 i 开始，从顶至底堆化 */
    public static void siftDown(int[] nums, int n, int i) {
        while (true) {
            // 获取左右节点索引
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int m = i;
            // 比较左右子树大小
            if (l < n && nums[l] > nums[m]) {
                m = l;
            }
            if (r < n && nums[r] > nums[m]) {
                m = r;
            }
            // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
            if (m == i) {
                break;
            }
            // 交换父子节点
            int tmp = nums[i];
            nums[i] = nums[m];
            nums[m] = tmp;
            // 循环向下堆化
            i = m;
        }
    }

    /* 堆排序 */
    public static void heapSort(int[] nums) {
        // 建堆操作：堆化除叶节点以外的其他所有节点
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }
        // 从堆中提取最大元素，循环 n-1 轮
        for (int i = nums.length - 1; i > 0; i--) {
            // 交换根节点与最右叶节点（交换首元素与尾元素）
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            // 以根节点为起点，从顶至底进行堆化
            siftDown(nums, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 3, 1, 5, 2};
        heapSort(nums);
        System.out.println("堆排序完成后 nums = " + Arrays.toString(nums));
    }
}
