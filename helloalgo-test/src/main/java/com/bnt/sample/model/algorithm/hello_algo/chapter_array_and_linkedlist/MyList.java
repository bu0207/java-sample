package com.bnt.sample.model.algorithm.hello_algo.chapter_array_and_linkedlist;

import java.util.Arrays;

/**
 * 列表实现
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/1 16:57 bnt
 * @history
 */
public class MyList {
    // 数组 存储元素
    private int[] arr;
    // 列表容量
    private int capacity = 10;
    // 列表长度（当前元素数量）
    private int size = 0;
    // 扩容倍数
    private int extendRatio = 2;

    /**
     * 构造方法
     */
    public MyList() {
        arr = new int[capacity];
    }

    /**
     * 获取列表长度(当前元素数量)
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取列表容量
     *
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * 访问元素
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return arr[index];
    }

    /**
     * 更新元素
     */
    public void set(int index, int num) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        arr[index] = num;
    }

    /**
     * 在尾部添加元素
     */
    public void add(int num) {
        if (size == capacity) {
            extendCapacity();
        }
        arr[size] = num;
        size++;
    }

    /**
     * 在中间插入元素
     */
    public void insert(int index, int num) {
        // 判断越界
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        // 判断是否扩容
        if (size == capacity) {
            extendCapacity();
        }
        // 将索引 index 以及之后的元素都向后移动一位
        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = num;
        // 更新元素数量
        size++;
    }

    /**
     * 删除元素
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        int num = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return num;
    }

    /**
     * 列表扩容
     */
    public void extendCapacity() {
        // 新建一个长度为原数组 extendRatio 倍的新数组 并将原数组复制到新数组
        arr = Arrays.copyOf(arr, getCapacity() * extendRatio);
        // 更新列表容量
        capacity = arr.length;
    }

    /**
     * 将列表转化为数组
     */
    public int[] toArray() {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }

    public static class MyListDemo {
        public static void main(String[] args) {
            /* 初始化列表 */
            MyList nums = new MyList();
            /* 在尾部添加元素 */
            nums.add(1);
            nums.add(3);
            nums.add(2);
            nums.add(5);
            nums.add(4);
            System.out.println("列表 nums = " + Arrays.toString(nums.toArray()) +
                    " ，容量 = " + nums.getCapacity() + " ，长度 = " + nums.getSize());

            /* 在中间插入元素 */
            nums.insert(3, 6);
            System.out.println("在索引 3 处插入数字 6 ，得到 nums = " + Arrays.toString(nums.toArray()));

            /* 删除元素 */
            nums.remove(3);
            System.out.println("删除索引 3 处的元素，得到 nums = " + Arrays.toString(nums.toArray()));

            /* 访问元素 */
            int num = nums.get(1);
            System.out.println("访问索引 1 处的元素，得到 num = " + num);

            /* 更新元素 */
            nums.set(1, 0);
            System.out.println("将索引 1 处的元素更新为 0 ，得到 nums = " + Arrays.toString(nums.toArray()));

            /* 测试扩容机制 */
            for (int i = 0; i < 10; i++) {
                // 在 i = 5 时，列表长度将超出列表容量，此时触发扩容机制
                nums.add(i);
            }
            System.out.println("扩容后的列表 nums = " + Arrays.toString(nums.toArray()) +
                    " ，容量 = " + nums.getCapacity() + " ，长度 = " + nums.getSize());
        }
    }
}
