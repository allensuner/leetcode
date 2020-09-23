package org.example.leetcode.rottingoranges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class App {

    private static class Cell {
        private final int row, column;

        public Cell(final int row, final int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Cell that = (Cell) o;
            return (
                (this.row == that.row) &&
                (this.column == that.column)
            );
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }

    public static void main(String[] args) {
        final int[][] grid = new int[][] {
            { 2, 1, 1 },
            { 1, 1, 0 },
            { 0, 1, 1 }
        };
        final int result = new App().orangesRotting(grid);
        System.out.println(result);
    }

    public int orangesRotting(final int[][] grid) {
        final Queue<Cell> orangesToCheck = new LinkedList<>();
        int freshOrangesCount = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                int state = grid[i][j];
                if (state == 1)
                    freshOrangesCount++;
                else if (state == 2)
                    orangesToCheck.offer(new Cell(i, j));
            }
        }

        int minutesElapsed = 0;

        while (!orangesToCheck.isEmpty() && freshOrangesCount > 0) {
            // each minute completely drain the queue and 'visit' the fresh orange neighbors
            final Set<Cell> nextCellsToCheck = new HashSet<>();
            while (!orangesToCheck.isEmpty()) {
                final Cell rottenOrange = orangesToCheck.poll();
                final int r = rottenOrange.row, c = rottenOrange.column;
                final Set<Cell> freshOrangeNeighbors = retrieveFreshNeighbors(r, c, grid);
                nextCellsToCheck.addAll(freshOrangeNeighbors);
            }

            // when the minute is over and all the new fresh oranges have been determined
            // queue them up to be visited the next minute
            for (final Cell cell : nextCellsToCheck) {
                final int r = cell.row, c = cell.column;
                grid[r][c] = 2;
                freshOrangesCount--;
                orangesToCheck.offer(cell);
            }

            minutesElapsed++;
        }

        if (freshOrangesCount > 0)
            return -1;

        return minutesElapsed;
    }

    private Set<Cell> retrieveFreshNeighbors(
            final int r,
            final int c,
            final int[][] grid
    ) {
        final Set<Cell> neighbors = new HashSet<>();

        final int upperBound = 0;
        final int lowerBound = grid.length - 1;
        final int leftBound = 0;
        final int rightBound = grid[0].length - 1;

        // top neighbor
        if ((r - 1 >= upperBound) && isFreshOrange(r - 1, c, grid))
            neighbors.add(new Cell(r - 1, c));

        // bottom neighbor
        if ((r + 1 <= lowerBound) && isFreshOrange(r + 1, c, grid))
            neighbors.add(new Cell(r + 1, c));

        // left neighbor
        if ((c - 1 >= leftBound) && isFreshOrange(r, c - 1, grid))
            neighbors.add(new Cell(r, c - 1));

        // right neighbor
        if ((c + 1 <= rightBound) && isFreshOrange(r , c + 1, grid))
            neighbors.add(new Cell(r,c + 1));

        return neighbors;
    }

    private boolean isFreshOrange(
        final int r,
        final int c,
        final int[][] grid
    ) {
        return (grid[r][c] == 1);
    }
}
