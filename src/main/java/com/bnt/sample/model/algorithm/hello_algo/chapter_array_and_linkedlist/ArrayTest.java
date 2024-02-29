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

    /**
     * 删除索引 index 处的元素
     */
    static void remove(int[] nums,int index){
        for (int i = index;i < nums.length-1;i++){
            nums[i] = nums[i+1];
        }
    }

    /**
     * 遍历数组
     */
    static void traverse(int[] nums){
        int count = 0;
        // 通过索引遍历数组
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
        }
        for (int num:nums){
            count +=num;
        }
    }

    /* 在数组中查找指定元素 */
    static int find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]){
                return i;
            }
        }
        return -1;
    }

    /* 扩展数组长度 */
    static int[] extend(int[] nums, int enlarge) {
        // 初始化一个扩展长度后的数组
        int[] res = new int[nums.length + enlarge];
        // 将原数组中的所有元素复制到新数组
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        return res;
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

        /* 删除元素 */
        remove(nums, 2);
        System.out.println("删除索引 2 处的元素，得到 nums = " + Arrays.toString(nums));

        /* 遍历数组 */
        traverse(nums);

        /* 查找元素 */
        int index = find(nums, 3);
        System.out.println("在 nums 中查找元素 3 ，得到索引 = " + index);

        /* 长度扩展 */
        nums = extend(nums, 3);
        System.out.println("将数组长度扩展至 8 ，得到 nums = " + Arrays.toString(nums));
    }
}
