package com.bnt.sample.model.algorithm.hello_algo.chapter_stack_and_queue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基于数组实现栈
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/4 10:21 bnt
 * @history
 */
class ArrayStackTest {
    private ArrayList<Integer> stack;

    public ArrayStackTest() {
        stack = new ArrayList<>();
    }

    /* 获取栈的长度 */
    public int size() {
        return stack.size();
    }

    /* 判断栈是否为空 */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /* 入栈 */
    public void push(int num) {
        stack.add(num);
    }

    /* 出栈 */
    public int pop() {
        return stack.remove(size() - 1);
    }

    /* 访问栈顶元素 */
    public int peek() {
        return stack.get(size() - 1);
    }

    /* 将 List 转化为 Array 并返回 */
    public Object[] toArray() {
        return stack.toArray();
    }
}

public class array_queue {
    public static void main(String[] args) {
        /* 初始化栈 */
        ArrayStackTest stack = new ArrayStackTest();

        /* 元素入栈 */
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        System.out.println("栈 stack = " + Arrays.toString(stack.toArray()));

        /* 访问栈顶元素 */
        int peek = stack.peek();
        System.out.println("栈顶元素 peek = " + peek);

        /* 元素出栈 */
        int pop = stack.pop();
        System.out.println("出栈元素 pop = " + pop + "，出栈后 stack = " + Arrays.toString(stack.toArray()));

        /* 获取栈的长度 */
        int size = stack.size();
        System.out.println("栈的长度 size = " + size);

        /* 判断是否为空 */
        boolean isEmpty = stack.isEmpty();
        System.out.println("栈是否为空 = " + isEmpty);
    }
}
