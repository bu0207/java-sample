package com.bnt.sample.model.algorithm.hello_algo.chapter_array_and_linkedlist;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 数组
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/2/29 13:53 bnt
 * @history
 */
public class ArrayTest {

    /**
     * 随机访问元素
     */
    static int randomAccess(int[] nums) {
        // 在区间 [0, nums.length) 中随机抽取一个数字
        int i = ThreadLocalRandom.current().nextInt(0, nums.length);
        return nums[i];
    }

    /**
     * 在数组的索引 index 处插入元素 num
     */
    static void insert(int[] nums, int index, int num) {
        for (int i = nums.length - 1; i > index; i--) {
            nums[i] = nums[i - 1];
        }
        nums[index] = num;
    }

    public static void main(String[] args) {
        /*初始化数组*/
        int[] arr = new int[5];
        System.out.println("数组 arr = " + Arrays.toString(arr));
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("数组 nums =" + Arrays.toString(nums));

        /*随机访问*/
        int randomNum = randomAccess(nums);
        System.out.println("在 nums 中获取随机元素=" + randomNum);

        insert(nums, 3, 6);
        System.out.println("在索引 3 处插入数字 6 ，得到 nums = " + Arrays.toString(nums));
    }
}
