package org.example.leetcode.island;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final char[][] grid = new char[][] {
            {'1','1','1','1','0'},
            {'1','1','0','1','1'},
            {'1','1','0','0','0'},
            {'0','0','1','0','1'}
        };
        final int numIslands = new App().numIslands(grid);
        System.out.println(numIslands);
    }

    public int numIslands(char[][] grid) {
        int numIslands = 0;
        final Set<Coordinate> visitedSet = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (isLand(i, j, grid) && !visitedSet.contains(new Coordinate(i, j))) {
                    dfs(i, j, grid, visitedSet);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }

    private void dfs(
        final int i,
        final int j,
        final char[][] grid,
        final Set<Coordinate> visitedSet
    ) {
        final Coordinate c = new Coordinate(i, j);
        // if we've already seen this coordinate, just return
        if (visitedSet.contains(c))
            return;

        // otherwise visit it
        visitedSet.add(c);
        // get it's neighbors
        final Set<Coordinate> neighbors = retrieveNeighbors(i, j, grid);
        // and go dfs on them too
        for (final Coordinate neighbor: neighbors) {
            dfs(neighbor.x, neighbor.y, grid, visitedSet);
        }
    }

    static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Coordinate that = (Coordinate) o;
            return this.x == that.x && this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private Set<Coordinate> retrieveNeighbors(
        final int r,
        final int c,
        final char[][] grid
    ) {
        final Set<Coordinate> neighbors = new HashSet<>();

        final int upperBound = 0;
        final int lowerBound = grid.length - 1;
        final int leftBound = 0;
        final int rightBound = grid[0].length - 1;

        // top neighbor
        if ((r - 1 >= upperBound) && isLand(r - 1, c, grid))
            neighbors.add(new Coordinate(r - 1, c));

        // bottom neighbor
        if ((r + 1 <= lowerBound) && isLand(r + 1, c, grid))
            neighbors.add(new Coordinate(r + 1, c));

        // left neighbor
        if ((c - 1 >= leftBound) && isLand(r, c - 1, grid))
            neighbors.add(new Coordinate(r, c - 1));

        // right neighbor
        if ((c + 1 <= rightBound) && isLand(r , c + 1, grid))
            neighbors.add(new Coordinate(r,c + 1));

        return neighbors;
    }

    private boolean isLand(
        final int r,
        final int c,
        final char[][] grid
    ) {
        return grid[r][c] == '1';
    }
}
