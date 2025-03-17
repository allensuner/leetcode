package org.example.leetcode.reverseinteger;

public class Solution {

    public static void main(String[] args) {
        final Solution s = new Solution();

        final int case1 = 123;
        int reversed = s.reverse(case1);
        int expected = 321;
        assert reversed == expected : "reversed is not " + expected + " it is " + reversed;

        final int case2 = -123;
        reversed = s.reverse(case2);
        expected = -321;
        assert reversed == expected : "reversed is not " + expected + " it is " + reversed;

        final int case3 = 120;
        reversed = s.reverse(case3);
        expected = 21;
        assert reversed == expected : "reversed is not " + expected + " it is " + reversed;
    }

    public int reverse(final int x) {
        final boolean isNegative = x < 0;
        final int startIndex = isNegative ? 1 : 0;
        final String stringRepresentation = String.valueOf(x);
        final StringBuilder sb = new StringBuilder();
        final int lastIndex = stringRepresentation.length() - 1;
        for (int i = lastIndex; i >= startIndex; --i) {
            sb.append(stringRepresentation.charAt(i));
        }
        final String reversedStringRepresentation = sb.toString();
        final String sanitized = reversedStringRepresentation.replaceAll("^0+", "");
        try {
            final int i = Integer.parseInt(sanitized);
            return isNegative ? -i : i;
        } catch (final NumberFormatException nfe) {
            return 0;
        }
    }
}
