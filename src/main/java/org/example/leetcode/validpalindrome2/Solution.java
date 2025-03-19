package org.example.leetcode.validpalindrome2;

public class Solution {

    public static void main(String[] args) {
        final String[] testCases = {
                "aba",      // true
                "abac",     // true
                "aaaaaz",   // true
                "abc",      // false
                "",         // true
                null        // false
        };

        final Solution s = new Solution();

        for (final String t: testCases) {
            System.out.println(s.validPalindrome(t));
        }
    }

    public boolean validPalindrome(String s) {

        if (s == null) {
            return false;
        }

        int left = 0, right = s.length() - 1;
        while (left < right) {
            final char leftChar = s.charAt(left);
            final char rightChar = s.charAt(right);
            if (leftChar != rightChar) {
                return (isPalindrome(left + 1, right, s) || isPalindrome(left, right - 1, s));
            }
            left++;
            right--;
        }

        return true;
    }

    private boolean isPalindrome(int start, int end, String s) {
        while (start < end) {
            final char leftChar = s.charAt(start);
            final char rightChar = s.charAt(end);
            if (leftChar != rightChar) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
