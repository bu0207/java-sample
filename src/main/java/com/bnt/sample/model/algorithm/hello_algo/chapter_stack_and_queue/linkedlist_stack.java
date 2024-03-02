package com.bnt.sample.model.algorithm.hello_algo.chapter_stack_and_queue;

import com.bnt.sample.model.algorithm.hello_algo.utils.ListNode;

import java.util.Arrays;

/**
 * 基于链表实现的栈
 */
class LinkedListStackTest {
    // 头节点作为栈顶
    private ListNode stackPeek;
    // 栈的长度
    private int stkSize = 0;

    public LinkedListStackTest() {
        stackPeek = null;
    }

    // 获取栈的长度
    public int size() {
        return stkSize;
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    /*入栈*/
    public void push(int num) {
        ListNode node = new ListNode(num);
        node.next = stackPeek;
        stackPeek = node;
        stkSize++;
    }

    /*出栈*/
    public int pop() {
        int num = peek();
        stackPeek = stackPeek.next;
        stkSize--;
        return num;
    }

    /*访问栈顶*/
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stackPeek.val;
    }


    /*将 List 转化为 Array 并返回 */
    public int[] toArray() {
        int[] array = new int[size()];
        int i = 0;
        ListNode node = stackPeek;
        while (node != null) {
            array[i] = node.val;
            node = node.next;
            i++;
        }
        return array;
    }
}

public class linkedlist_stack {
    public static void main(String[] args) {
        /* 初始化栈 */
        LinkedListStackTest stack = new LinkedListStackTest();

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
