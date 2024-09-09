package com.bnt.sample.model.algorithm.hello_algo.chapter_tree;


import com.bnt.sample.model.algorithm.hello_algo.utils.PrintUtil;
import com.bnt.sample.model.algorithm.hello_algo.utils.TreeNode;

/**
 * 二叉树基本操作
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/6 15:14 bnt
 * @history
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        /*初始化二叉树*/
        // 初始化节点
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        // 构建节点之间的引用
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(n1);

        /*插入删除节点*/
        // 在 n1 -> n2 中间插入节点 P
        TreeNode p = new TreeNode(0);
        n1.left = p;
        p.left = n2;
        System.out.println("\n插入节点 P 后\n");
        PrintUtil.printTree(n1);
        // 删除节点P
        n1.left = n2;
        System.out.println("\n删除节点 P 后\n");
        PrintUtil.printTree(n1);
    }
}
