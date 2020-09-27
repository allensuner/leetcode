package org.example.leetcode.optimalmeetingpoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {
        final int[][] grid = new int[][] {
            { 1, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0 }
        };
        System.out.println(new App().minTotalDistance(grid));
    }

    public int minTotalDistance(int[][] grid) {
        final List<Integer> rows = new ArrayList<>(), columns = new ArrayList<>();
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j)
                if (grid[i][j] == 1) {
                    rows.add(i);
                    columns.add(j);
                }

        return (minDistance(rows) + minDistance(columns));
    }

    private int minDistance(final List<Integer> houses) {
        Collections.sort(houses);
        final int median = houses.get(houses.size() / 2);
        return houses.stream().mapToInt(house -> Math.abs(house - median)).sum();
    }
}
