package org.example.leetcode.mergewordsalternatively;

public class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        final String[][] testCases = {
            {"abc", "pqr", "apbqcr"},
            {"ab", "pqrd", "apbqrd"},
        };
        for (String[] testCase : testCases) {
            final String w1 = testCase[0];
            final String w2 = testCase[1];
            final String result = solution.mergeAlternately(w1, w2);
            final String expected = testCase[2];
            assert result.equals(expected) : "result is not " + expected + " it is " + result;

        }
    }

    public String mergeAlternately(String word1, String word2) {
        int i = 0, j = 0;
        final int n = word1.length(), m = word2.length();
        final StringBuilder sb = new StringBuilder();
        while (i < n && j < m) {
            sb.append(word1.charAt(i++));
            sb.append(word2.charAt(j++));
        }

        while (i < n) {
            sb.append(word1.charAt(i++));
        }

        while (j < m) {
            sb.append(word2.charAt(j++));
        }

        return sb.toString();
    }
}
