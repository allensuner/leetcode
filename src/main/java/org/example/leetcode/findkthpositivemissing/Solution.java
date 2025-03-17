package org.example.leetcode.findkthpositivemissing;

public class Solution {

    public static void main(String[] args) {
        final int[] test = { 2, 3, 4, 7, 11 };
        final int result = new Solution().findKthPositive(test, 2);
        System.out.println(result);
    }

    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            final int mid = left + (right - left) / 2;
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left + k;
    }
}
