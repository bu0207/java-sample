package com.bnt.sample.model.algorithm.hello_algo.chapter_searching;

/**
 * 二分查找边界
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/1 13:41 bnt
 * @history
 */
public class binary_search_edge {

    /**
     * 给定一个长度为 n 的有序数组 nums ，其中可能包含重复元素。
     * 请返回数组中最左一个元素 target 的索引。若数组中不包含该元素，则返回 −1 。
     */
    /* 二分查找最左一个 target */
    static int binarySearchLeftEdge(int[] nums, int target) {
        // 等价于查找target的插入点
        int i = binary_search_insertion.binarySearchInsertion(nums, target);
        // 未找到target，返回-1
        if (i == nums.length || nums[i] != target) {
            return -1;
        }
        return i;
    }

    /* 二分查找最右一个 target */ /*转化为查找最左一个target+1*/
    static int binarySearchRightEdge(int[] nums, int target) {
        // 转化为查找最左一个target+1
        int i = binary_search_insertion.binarySearchInsertion(nums, target + 1);
        // j 指向最右一个 target ，i 指向首个大于 target 的元素
        int j = i - 1;
        // 未找到target，返回-1
        if (j == -1 || nums[j] != target) {
            return -1;
        }
        return j;
    }

    /*二分查找最右一个target*/
    static int binarySearchRightEdge2(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] < target) {
                i = m + 1;
            } else if (nums[m] > target) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        if (j == -1 || nums[j] != target) {
            return -1;
        }
        return j;
    }

    public static void main(String[] args) {
        // 包含重复元素的数组
        int[] nums = {1, 3, 6, 6, 6, 6, 6, 10, 12, 15};
        System.out.println("\n数组 nums = " + java.util.Arrays.toString(nums));

        // 二分查找左边界和右边界
        for (int target : new int[]{6, 7, 0, 18}) {
            int index = binarySearchLeftEdge(nums, target);
            System.out.println("最左一个元素 " + target + " 的索引为 " + index);
            index = binarySearchRightEdge(nums, target);
            System.out.println("最右一个元素 " + target + " 的索引为 " + index);
            index = binarySearchRightEdge2(nums, target);
            System.out.println("最右一个元素 " + target + " 的索引为 " + index);
        }
    }
}
