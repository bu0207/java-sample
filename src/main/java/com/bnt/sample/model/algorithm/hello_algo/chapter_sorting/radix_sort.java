package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.Arrays;

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
        // 十进制的位范围为 0~9 ，因此需要长度为 10 的桶数组
        int[] counter = new int[10];
        int n = nums.length;
        // 统计 0~9 各数字的出现次数
        for (int i = 0; i < n; i++) {
            int d = digit(nums[i], exp); // 获取 nums[i] 第 k 位，记为 d
            counter[d]++;                // 统计数字 d 的出现次数
        }
        // 求前缀和,将“出现个数”转换为“数组索引”
        for (int i = 0; i < counter.length - 1; i++) {
            counter[i + 1] += counter[i];
        }
        // 倒序遍历，根据桶内统计结果，将各元素填入 res
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int d = digit(nums[i], exp);
            int j = counter[d] - 1; // 获取 d 在数组中的索引 j
            res[j] = nums[i];       // 将当前元素填入索引 j
            counter[d]--;           // 将 d 的数量减 1
        }
    }

    /* 基数排序 */
    static void radixSort(int[] nums) {
        // 获取数组的最大元素，用于判断最大位数
        int m = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > m) {
                m = num;
            }
        }
        // 按照从低位到高位的顺序遍历
        for (int exp = 1; exp <= m; exp *= 10) {
            // 对数组元素的第 k 位执行计数排序
            // k = 1 -> exp = 1
            // k = 2 -> exp = 10
            // 即 exp = 10^(k-1)
            countingSortDigit(nums, exp);
        }
    }

    public static void main(String[] args) {
        // 基数排序
        int[] nums = { 10546151, 35663510, 42865989, 34862445, 81883077,
                88906420, 72429244, 30524779, 82060337, 63832996 };
        radixSort(nums);
        System.out.println("基数排序完成后 nums = " + Arrays.toString(nums));
    }
}
