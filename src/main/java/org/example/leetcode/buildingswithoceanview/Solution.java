package org.example.leetcode.buildingswithoceanview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        final int[][] testCases = {
                {4,2,3,1},
                {4,3,2,1},
                {1,3,2,4},
                {},
                null,
        };
        Solution s = new Solution();
        for (final int[] t: testCases) {
            final int[] result = s.findBuildings(t);
            System.out.println(Arrays.toString(result));
        }
    }

    public int[] findBuildings(int[] heights) {
        if (heights == null) {
            return new int[] {};
        }

        final List<Integer> indices = new ArrayList<>();

        int i = heights.length - 1;
        int max = Integer.MIN_VALUE;
        while (i >= 0) {
            final int currHeight = heights[i];
            if (currHeight > max) {
                indices.add(i);
                max = currHeight;
            }
            i--;
        }

        final int[] result = new int[indices.size()];
        int li = indices.size() - 1;
        i = 0;
        while (li >= 0) {
            result[i] = indices.get(li);
            li--;
            i++;
        }

        return result;
    }
}
