package org.example.leetcode.validwordabbreviation;

public class Solution {
    public static void main(String[] args) {
        final String[][] testCases = {
                {"internationalization", "i12iz4n"},    // true
                {"apple", "a2e"},                       // false
                {"internationalization", "i5a11o1"},    // true
                {"word", "w0ord"}                       // false
        };

        final Solution s = new Solution();

        for (final String[] t: testCases) {
            final String word = t[0], abbr = t[1];
            final boolean result = s.validWordAbbreviation(word, abbr);
            System.out.println(result);
        }
    }

    public boolean validWordAbbreviation(String word, String abbr) {

        int wIdx = 0, aIdx = 0;
        final int wordLen = word.length(), abbrLen = abbr.length();

        while (wIdx < word.length() && aIdx < abbr.length()) {
            if (word.charAt(wIdx) == abbr.charAt(aIdx)) {
                wIdx++; aIdx++;
                continue;
            }

            if (!Character.isDigit(abbr.charAt(aIdx))) {
                return false;
            }

            final int replacements = getNextNumber(aIdx, abbr);
            if (replacements == 0)
                return false;

            wIdx += replacements;
            aIdx += String.valueOf(replacements).length();
        }

        return (wIdx == wordLen && aIdx == abbrLen);
    }

    private int getNextNumber(int start, String s) {
        int i = start;
        final StringBuilder sb = new StringBuilder();
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            sb.append(s.charAt(i));
            i++;
        }
        final String asString = sb.toString();
        return Integer.parseInt(asString);
    }
}
