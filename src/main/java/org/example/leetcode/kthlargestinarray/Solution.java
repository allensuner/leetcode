package org.example.leetcode.kthlargestinarray;

import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        final int[][] testCaseArrays = {
                {3,2,1,5,6,4},          // 5
                {3,2,3,1,2,4,5,5,6},    // 4
        };
        final int[] testCaseKs = {2, 4};

        final Solution s = new Solution();
        for (int i = 0; i < testCaseKs.length; ++i) {
            final int[] nums = testCaseArrays[i];
            final int k = testCaseKs[i];
            final int result = s.findKthLargest(nums, k);
            System.out.println(result);
        }
    }

    /*
     * maintain a min heap of size k, iterate over nums, adding each element to the minHeap
     */
    public int findKthLargest(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for(final int x: nums) {
            final int n = minHeap.size();
            if (n == k) {
                final int top = minHeap.poll();
                if (top > x) {
                    minHeap.offer(top);
                    continue;
                }
            }
            minHeap.offer(x);
        }
        return minHeap.peek();
    }
}
