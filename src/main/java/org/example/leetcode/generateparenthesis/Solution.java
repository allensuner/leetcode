package org.example.leetcode.generateparenthesis;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int[] testCases = {2, 3, 4};
        Solution s = new Solution();
        for (final int t: testCases) {
            final List<String> result = s.generateParenthesis(t);
            System.out.printf("test case: %d%n", t);
            System.out.println(result);
            System.out.println();
        }
    }

    public List<String> generateParenthesis(int n) {
        final List<String> result = new ArrayList<>();
        backtrack(result, 0, 0, "", n);
        return result;
    }



    public void backtrack(List<String> res, int left, int right, String s, int n) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }

        if (left < n) {
            backtrack(res, (left + 1), right, (s + "("), n);
        }

        if (right < left) {
            backtrack(res, left, (right + 1), (s + ")"), n);
        }
    }
}
