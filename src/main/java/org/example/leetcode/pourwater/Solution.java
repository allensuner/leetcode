package org.example.leetcode.pourwater;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        final int[] heights = { 2, 1, 1, 2, 1, 2, 2 };
        final int volume = 4;
        final int k = 3;
        final int[] result = new Solution().pourWater(heights, volume, k);
        System.out.println(Arrays.toString(result));
    }

    public int[] pourWater(final int[] heights, final int volume, final int k) {
        // for each unit of water
        for (int i = 0; i < volume; ++i) {
            int curr = k;

            // move left
            while (curr > 0 && heights[curr] >= heights[curr - 1]) {
                curr--;
            }

            // move right
            while (curr < heights.length - 1 && heights[curr] >= heights[curr + 1]) {
                curr++;
            }

            // move left to k
            while (curr > k && heights[curr] >= heights[curr - 1]) {
                curr--;
            }

            heights[curr]++;
        }

        return heights;
    }
}
