package com.bnt.sample.model.algorithm.hello_algo.chapter_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序
 *
 *
 * 它通过设置一些具有大小顺序的桶，每个桶对应一个数据范围，将数据平均分配到各个桶中；
 * 然后，在每个桶内部分别执行排序；最终按照桶的顺序将所有数据合并。
 * @author bnt
 * @version 1.0.0
 * @create 2024/4/8 16:58 bnt
 * @history
 */
public class bucket_sort {
    /* 桶排序 */
    static void bucketSort(float[] nums) {
        // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }
        // 1. 将数组元素分配到各个桶中
        for (float num : nums) {
            // 输入数据范围为 [0, 1)，使用 num * k 映射到索引范围 [0, k-1]
            int i = (int) num * k;
            // 将 num 添加进桶 i
            buckets.get(i).add(num);
        }
        // 2. 对各个桶执行排序
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }
        // 3. 遍历桶合并结果
        int j = 0;
        for (List<Float> bucket : buckets) {
            for (Float aFloat : bucket) {
                nums[j++] = aFloat;
            }
        }
    }

    public static void main(String[] args) {
        // 设输入数据为浮点数，范围为 [0, 1)
        float[] nums = {0.49f, 0.96f, 0.82f, 0.09f, 0.57f, 0.43f, 0.91f, 0.75f, 0.15f, 0.37f};
        bucketSort(nums);
        System.out.println("桶排序完成后 nums = " + Arrays.toString(nums));
    }
}
