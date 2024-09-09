package com.bnt.sample.model.algorithm.hello_algo.chapter_tree;

import com.bnt.sample.model.algorithm.hello_algo.utils.PrintUtil;
import com.bnt.sample.model.algorithm.hello_algo.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组表示二叉树
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/8 13:50 bnt
 * @history
 */
class ArrayBinaryTree {
    private List<Integer> tree;

    public ArrayBinaryTree(List<Integer> arr) {
        tree = new ArrayList<>(arr);
    }

    /*列表容量*/
    public int size() {
        return tree.size();
    }

    /*获取索引为i的节点*/
    public Integer val(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }
        return tree.get(i);
    }

    /* 获取索引为 i 节点的左子节点的索引 */
    public int left(int i) {
        return 2 * i + 1;
    }

    /* 获取索引为 i 节点的右子节点的索引 */
    public int right(int i) {
        return 2 * i + 2;
    }

    /* 获取索引为 i 节点的父节点的索引 */
    public int parent(int i) {
        return (i - 1) / 2;
    }

    /* 层序遍历 */
    public List<Integer> levelOrder() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (val(i) == null) {
                continue;
            }
            list.add(val(i));
        }
        return list;
    }

    /* 深度优先遍历 */
    private void dfs(Integer i, String order, List<Integer> res) {
        if (val(i) == null) {
            return;
        }
        if (order.equals("pre")) {
            res.add(val(i));
        }
        dfs(left(i), order, res);
        if (order.equals("in")) {
            res.add(val(i));
        }
        dfs(right(i), order, res);
        if (order.equals("post")) {
            res.add(val(i));
        }
    }

    /* 前序遍历 */
    public List<Integer> preOrder() {
        List<Integer> list = new ArrayList<>();
        dfs(0, "pre", list);
        return list;
    }

    /* 中序遍历 */
    public List<Integer> inOrder() {
        List<Integer> list = new ArrayList<>();
        dfs(0, "in", list);
        return list;
    }

    /* 后序遍历 */
    public List<Integer> postOrder() {
        List<Integer> list = new ArrayList<>();
        dfs(0, "post", list);
        return list;
    }
}

public class array_binary_tree {
    public static void main(String[] args) {
        // 初始化二叉树
        // 这里借助了一个从数组直接生成二叉树的函数
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, null, 6, 7, 8, 9, null, null, 12, null, null, 15);

        TreeNode root = TreeNode.listToTree(arr);
        System.out.println("\n初始化二叉树\n");
        System.out.println("二叉树的数组表示：");
        System.out.println(arr);
        System.out.println("二叉树的链表表示：");
        PrintUtil.printTree(root);

        // 数组表示下的二叉树类
        ArrayBinaryTree abt = new ArrayBinaryTree(arr);

        // 访问节点
        int i = 1;
        Integer l = abt.left(i);
        Integer r = abt.right(i);
        Integer p = abt.parent(i);
        System.out.println("\n当前节点的索引为 " + i + " ，值为 " + abt.val(i));
        System.out.println("其左子节点的索引为 " + l + " ，值为 " + (l == null ? "null" : abt.val(l)));
        System.out.println("其右子节点的索引为 " + r + " ，值为 " + (r == null ? "null" : abt.val(r)));
        System.out.println("其父节点的索引为 " + p + " ，值为 " + (p == null ? "null" : abt.val(p)));

        // 遍历树
        List<Integer> res = abt.levelOrder();
        System.out.println("\n层序遍历为：" + res);
        res = abt.preOrder();
        System.out.println("前序遍历为：" + res);
        res = abt.inOrder();
        System.out.println("中序遍历为：" + res);
        res = abt.postOrder();
        System.out.println("后序遍历为：" + res);
    }
}
