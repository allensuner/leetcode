package org.example.leetcode.flatten2dvector;

class Vector2D {

    private final int[][] vector;
    int row, col, totalRows;

    public Vector2D(final int[][] vec) {
        this.row = 0;
        this.col = 0;
        this.vector = vec;
        this.totalRows = vec.length;
    }

    public int next() {
        final int[] currRow = vector[row];
        int toReturn;
        if (col < currRow.length){
            toReturn = currRow[col];
            col += 1;
            if (col == currRow.length){
                row += 1;
                col = 0;
            }
        } else {
            row += 1;
            col = 0;
            toReturn = vector[row][col];
            col += 1;
        }
        return toReturn;
    }

    public boolean hasNext() {
        if (row < totalRows) {
            if (col < vector[row].length)
                return true;
            row += 1;
            return hasNext();
        }
        final int lastRow = totalRows - 1;
        final boolean isLastRow = (row == lastRow);
        if (isLastRow) {
            return col < vector[lastRow].length;
        }
        return false;
    }

    public static void main(String[] args) {
        final int[][] vec = new int[][] {
            { },
            { },
            { 1, 2 },
            { 3 },
            { 4, 5, 6 },
            { }
        };
        final Vector2D vector2D = new Vector2D(vec);
        final StringBuilder sb = new StringBuilder();
        while (vector2D.hasNext()) {
            sb.append(vector2D.next()).append(", ");
        }
        final String result = sb.toString();
        System.out.println(result);
    }
}