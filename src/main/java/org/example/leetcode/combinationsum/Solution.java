package org.example.leetcode.combinationsum;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(final int[] nums, final int target) {
        final List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, target, 0);
        return result;
    }

    private void backtrack(
        final List<List<Integer>> list,
        final List<Integer> tempList,
        final int[] nums,
        final int remain,
        final int start
    ) {
        if (remain >= 0) {
            if (remain == 0) {
                list.add(new ArrayList<>(tempList));
            } else {
                for (int i = start; i < nums.length; ++i){
                    final int currentNum = nums[i];
                    tempList.add(currentNum);
                    final int currentRemain = remain - currentNum;
                    backtrack(list, tempList, nums, currentRemain, i);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
}