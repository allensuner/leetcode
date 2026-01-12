package org.example.leetcode.subarraysumequalsk;

public class Solution {

    private static class TestCase {
        int[] nums;
        int k;
        int expectedResult;
        public TestCase(int[] nums, int k, int expectedResult) {
            this.nums = nums;
            this.k = k;
            this.expectedResult = expectedResult;
        }
    }

    public static void main(String[] args) {
        final TestCase[] testCases = {
            new TestCase(new int[]{1, 1, 1}, 2, 2),
            new TestCase(new int[]{1,2,3}, 3, 2),
        };
        final Solution solution = new Solution();
        for (final TestCase tc : testCases) {
            final int result = solution.subarraySum(tc.nums, tc.k);
            assert result == tc.expectedResult : ("subarray sum should equal " + tc.expectedResult + " but is " + result);
        }
    }

    public int subarraySum(int[] nums, int k) {
        final int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1; i <= nums.length; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int count = 0;
        final int n = sums.length;
        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end < n; end++) {
                if (sums[end] - sums[start] == k) {
                    count++;
                }
            }
        }
        return count;
    }

}
