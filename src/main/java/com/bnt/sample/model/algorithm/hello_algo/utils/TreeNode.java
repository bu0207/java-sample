package com.bnt.sample.model.algorithm.hello_algo.utils;

import java.util.List;

/**
 * 二叉树节点类
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/6 15:17 bnt
 * @history
 */
public class TreeNode {
    public int val;

    public int height;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }


    /* 将列表反序列化为二叉树：递归 */
    private static TreeNode listToTreeDFS(List<Integer> arr, int i) {
        if (i < 0 || i >= arr.size() || arr.get(i) == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr.get(i));
        root.left = listToTreeDFS(arr, 2 * i + 1);
        root.right = listToTreeDFS(arr, 2 * i + 2);
        return root;
    }

    /* 将列表反序列化为二叉树 */
    public static TreeNode listToTree(List<Integer> arr) {
        return listToTreeDFS(arr, 0);
    }
}
