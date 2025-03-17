package org.example.leetcode.diameterofbinarytree;

class Solution {
    int diameter;

    public int diameterOfBinaryTree(final TreeNode root) {
        diameter = 0;
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(final int val) {
            this.val = val;
        }
        TreeNode(
            final int val,
            final TreeNode left,
            final TreeNode right
        ) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
