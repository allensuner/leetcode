package org.example.leetcode.validpalindrome;

public class Solution {
    public static void main(String[] args) {
        final String input = "A man, a plan, a canal: Panama";
        System.out.println(new Solution().isPalindrome(input));
    }

    /*
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
     * non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters
     * and numbers. Given a string s, return true if it is a palindrome, or false otherwise.
     *
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     *
     * Input: s = "race a car"
     * Output: false
     * Explanation: "raceacar" is not a palindrome.
     *
     * Input: s = " "
     * Output: true
     * Explanation: s is an empty string "" after removing non-alphanumeric characters.
     * Since an empty string reads the same forward and backward, it is a palindrome.
     */
    public boolean isPalindrome(final String s) {
        if (s == null) {
            return true;
        }

        final String clean = s.replaceAll("[^A-Za-z\\d]+", "").toLowerCase();

        int r = clean.length() - 1, l = 0;
        while (r > l) {
            char front = clean.charAt(l);
            char back = clean.charAt(r);

            if (front != back) {
                return false;
            }
            r--;
            l++;
        }
        return true;
    }

}
