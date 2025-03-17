package org.example.leetcode.foursum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        final Solution s = new Solution();

        /*
         * Example 1:
         * Input: nums = [1,0,-1,0,-2,2], target = 0
         * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
         *
         * Example 2:
         * Input: nums = [2,2,2,2,2], target = 8
         * Output: [[2,2,2,2]]
         */

        List<List<Integer>> result = s.fourSum(new int[]{1,0,-1,0,-2,2}, 0);
        Set<Set<Integer>> expected = new HashSet<>();
        expected.add(new HashSet<>(Arrays.asList(-2,-1,1,2)));
        expected.add(new HashSet<>(Arrays.asList(-2,0,0,2)));
        expected.add(new HashSet<>(Arrays.asList(-1,0,0,1)));

        assert assertEqual(result, expected) : "result is not " + expected + " it is " + result;

        result = s.fourSum(new int[]{2,2,2,2,2}, 8);
        expected = new HashSet<>();
        expected.add(new HashSet<>(Arrays.asList(2,2,2,2)));

        assert assertEqual(result, expected) : "result is not " + expected + " it is " + result;
    }

    private static boolean assertEqual(final List<List<Integer>> result, final Set<Set<Integer>> expected) {
        final Set<Set<Integer>> deepCopy = new HashSet<>();
        for (final List<Integer> integers : result) {
            deepCopy.add(new HashSet<>(integers));
        }

        return deepCopy.equals(expected);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return new ArrayList<>();
    }
}
