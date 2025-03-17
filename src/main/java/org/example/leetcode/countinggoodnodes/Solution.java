package org.example.leetcode.countinggoodnodes;

class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int goodNodes(final TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    private int goodNodes(final TreeNode root, final int previousValue) {
        if (root == null) {
            return 0;
        }
        int nodes = 0;
        if (root.val >= previousValue) {
            nodes++;
        }
        final int goodNodesInLeftSubtree = goodNodes(root.left, Math.max(root.val, previousValue));
        final int goodNodesInRightSubtree = goodNodes(root.right, Math.max(root.val, previousValue));
        return (nodes + goodNodesInLeftSubtree + goodNodesInRightSubtree);
    }

}
