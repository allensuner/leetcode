package org.example.leetcode.rangesumbst;

public class Solution {
    int sum;
    /*
     * Given the root node of a binary search tree and two integers low and high, return the sum of values of
     * all nodes with a value in the inclusive range [low, high].
     *
     *       10
     *     /    \
     *    5      15
     *  /  \       \
     * 3    7       18
     *
     * if given the tree above and low = 7 and high = 10
     * the sum here is 17 because 7 + 10 = 17
     *
     *          10
     *        /    \
     *       5      15
     *     /  \    /  \
     *    3    7  13   18
     *   /    /
     *  1    6
     *
     * if given this tree however and low = 6 and high = 14
     * then the sum here is 37 because 6 + 7 + 10 + 14 = 37
     */
    public int rangeSumBST(TreeNode root, int low, int high) {

        // base case - root is null (we've gotten to a leaf)

        // check to see if root is in range, if it is add to the sum
        // and recurse on both left and right

        // if it's not then check root value against low and high

        // if root is less than low then summing values must be in the right subtree so recurse only on the right subtree

        // if root is greater than high then summing values must be in the left subtree so recurse only on the left subtree
        sum = 0;
        helper(root, low, high);
        return sum;
    }

    private void helper(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }

        int val = root.val;
        if (val <= high && val >= low) {
            sum += val;
            helper(root.left, low, high);
            helper(root.right, low, high);
        } else if (val > high) {
            helper(root.left, low, high);
        } else {
            helper(root.right, low, high);
        }
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
                TreeNode left,
                TreeNode right
        ) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
