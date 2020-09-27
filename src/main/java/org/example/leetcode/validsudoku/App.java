package org.example.leetcode.validsudoku;

public class App {
    public static void main(String[] args) {
        final char[][] board = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new App().isValidSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {
        final int n = board.length;
        final int[][] rows = new int[n][n];
        final int[][] columns = new int[n][n];
        final int[][] boxes = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                final int box = (row / 3) * 3 + column / 3;
                final char cv = board[row][column];
                if (Character.isDigit(cv)) {
                    final int value = Character.getNumericValue(cv) - 1;
                    final int cr = ++rows[row][value];
                    final int cc = ++columns[column][value];
                    final int cb = ++boxes[box][value];
                    if (cr > 1 || cc > 1 || cb > 1)
                        return false;
                }
            }
        }

        return true;
    }
}
