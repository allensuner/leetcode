package org.example.leetcode.besttimetotradestock;

public class Solution {

    private static class TestCase {
        int[] prices;
        int expectedResult;
        TestCase(int[] prices, int expectedResult) {
            this.prices = prices;
            this.expectedResult = expectedResult;
        }
    }

    public static void main(String[] args) {
        final Solution s = new Solution();
        final TestCase[] testCases = {
            new TestCase(new int[] {7,1,5,3,6,4}, 5),
            new TestCase(new int[] {7,6,4,3,1}, 0),
        };
        for (final TestCase tc: testCases) {
            final int[] prices = tc.prices;
            final int expected = tc.expectedResult;
            final int actual = s.maxProfit(prices);
            assert expected == actual : ("profit expected to be " + expected + " but was " + actual);
        }
    }

    public int maxProfit(int[] prices) {
        int overallProfit = 0;
        int leastSoFar = Integer.MAX_VALUE;
        for (final int price : prices) {
            if (price < leastSoFar) {
                leastSoFar = price;
            }
            final int profitIfSoldToday = (price - leastSoFar);
            if (profitIfSoldToday > overallProfit) {
                overallProfit = profitIfSoldToday;
            }
        }
        return overallProfit;
    }
}
