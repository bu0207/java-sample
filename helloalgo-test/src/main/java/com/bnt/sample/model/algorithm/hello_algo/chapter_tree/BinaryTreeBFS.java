package com.bnt.sample.model.algorithm.hello_algo.chapter_tree;

import com.bnt.sample.model.algorithm.hello_algo.utils.PrintUtil;
import com.bnt.sample.model.algorithm.hello_algo.utils.TreeNode;

import java.util.*;

/**
 * 树的广度优先遍历-层序遍历
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/6 18:10 bnt
 * @history
 */
public class BinaryTreeBFS {
    static List<Integer> levelOrder(TreeNode root) {
        // 初始化队列，加入根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化一个列表，用于保存遍历序列
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 队列出队
            TreeNode node = queue.poll();
            // 保存节点值
            res.add(node.val);
            // 左子节点入队
            if (node.left != null) {
                queue.add(node.left);
            }
            // 右子节点入队
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /* 初始化二叉树 */
        // 这里借助了一个从数组直接生成二叉树的函数
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(root);

        /* 层序遍历 */
        List<Integer> list = levelOrder(root);
        System.out.println("\n层序遍历的节点打印序列 = " + list);
    }
}
