package org.example.leetcode.maxscoresubstring;

public class App {
    public static void main(final String[] args) {
        final App app = new App();
        final String s = "011101";
        final int result = app.maxScore(s);
        assert result == 5 : "result should be 5";
    }

    public int maxScore(final String s) {
        final int n = s.length();
        final int lastIndex = n - 1;

        int maxScore = 0;
        int leftSideZeros = 0;
        int rightSideOnes = (int) s.chars().filter(c -> c == '1').count();

        for (int i = 0; i < lastIndex; ++i) {
            final char curr = s.charAt(i);
            leftSideZeros += (curr == '0') ? 1 : 0;
            rightSideOnes -= (curr == '1') ? 1 : 0;
            final int currentSum = leftSideZeros + rightSideOnes;
            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }
}
