package org.example.leetcode.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        final int[][] intervals = {
                {1, 3},
                {0, 1},
                {7, 8},
                {5, 6},
                {6, 7},
                {10, 15},
                {8, 9}
        };

        final int[][] expected = {
                {0, 3},
                {5, 9},
                {10, 15}
        };

        final Solution s = new Solution();

        final int[][] result = s.merge(intervals);
        System.out.println(Arrays.deepEquals(expected, result));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        Arrays.parallelSort(intervals, Comparator.comparingInt(a -> a[0]));

        final List<int[]> result = new ArrayList<>();

        int[] current = intervals[0];
        final int n = intervals.length;
        for(int i = 0; i < n; ++i) {
            if (isOverlap(current, intervals[i])) {
                current = mergeOverlapping(current, intervals[i]);
            } else {
                result.add(current);
                current = intervals[i];
            }
        }

        result.add(current);

        final int[][] resultArray = new int[result.size()][2];
        for (int i = 0; i < result.size(); ++i) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    private boolean isOverlap(int[] before, int[] after) {
        return before[1] >= after[0];
    }

    private int[] mergeOverlapping(int[] before, int[] after) {
        return new int[]{before[0], Math.max(before[1], after[1])};
    }
}
