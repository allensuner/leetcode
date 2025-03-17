package org.example.leetcode.reorganizestring;

public class Solution {
    public static void main(String[] args) {
        final String test = "bfrbs";
        final String result = new Solution().reorganizeString(test);
        System.out.println(result);
    }

    public String reorganizeString(String s) {
        // create a frequency map of each letter (26 letters)
        final int[] hash = new int[26];
        // populate the frequency map
        final int n = s.length();
        for (int i = 0; i < n; ++i) {
            final char c = s.charAt(i);
            final int index = convertFromChar(c);
            hash[index]++;
        }

        // find the largest character in the hash and it's frequency
        int mostFrequentCharacter = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < hash.length; ++i) {
            final int value = hash[i];
            if (value > max) {
                max = value;
                mostFrequentCharacter = i;
            }
        }

        // if the character occurs *more* than half the length of the input string this is impossible - return empty string
        final int maxOccurances = (n + 1) / 2;
        if (max > maxOccurances) {
            return "";
        }

        // initialize result array
        final char[] result = new char[n];

        // the most frequent character should be placed in the event indices
        // (drain this as it will be useful for next step)
        int index = 0;
        for (int i = 0; i < max; ++i) {
            result[index] = convertToChar(mostFrequentCharacter);
            hash[mostFrequentCharacter]--;
            index += 2;
        }

        // the rest of the characters can go in the other indices (in whatever order - draining each bucket)
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (index >= result.length) {
                    index = 1;
                }
                result[index] = convertToChar(i);
                index += 2;
                hash[i]--;
            }
        }

        return String.valueOf(result);
    }

    private char convertToChar(final int x) {
        return (char) (x + 'a');
    }

    private int convertFromChar(final char c) {
        return c - 'a';
    }
}
