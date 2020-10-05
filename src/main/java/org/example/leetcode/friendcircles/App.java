package org.example.leetcode.friendcircles;

import java.util.HashSet;
import java.util.Set;

class App {

    public static void main(String[] args) {
        final int[][] matrix = {
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        };
        final int numCircles = new App().findCircleNum(matrix);
        System.out.println(numCircles);
    }

    public int findCircleNum(final int[][] M) {
        int numCircles = 0;
        final Set<Integer> visitedSet = new HashSet<>();
        for (int friend = 0; friend < M.length; ++friend) {
            // explore friend's circle by doing dfs
            if (!visitedSet.contains(friend)) {
                dfs(friend, M, visitedSet);
                numCircles++;
            }
        }

        return numCircles;
    }

    private void dfs(final int friendNumber, final int[][] matrix, final Set<Integer> visitedSet) {
        if (visitedSet.contains(friendNumber))
            return;
        visitedSet.add(friendNumber);
        for (int j = 0; j < matrix[friendNumber].length; ++j)
            if (matrix[friendNumber][j] == 1)
                dfs(j, matrix, visitedSet);
    }
}