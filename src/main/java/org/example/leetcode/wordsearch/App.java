package org.example.leetcode.wordsearch;

public class App {

    /*
        board =
        [
          ['A','B','C','E'],
          ['S','F','C','S'],
          ['A','D','E','E']
        ]

        Given word = "ABCCED", return true.
        Given word = "SEE", return true.
        Given word = "ABCB", return false.
     */
    public static void main(String[] args) {
        final char[][] board = {
          {'A','B','C','E'},
          {'S','F','C','S'},
          {'A','D','E','E'}
        };
//        final String[] words = { "ABCCED", "SEE", "ABCD" };
        final String[] words = { "ABCCED" };
        final App a = new App();
        for (final String word : words)
            System.out.println(a.exist(board, word));
    }

    public boolean exist(final char[][] board, final String word) {
        final char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x + 1, word, i + 1) // right neighbor
                || exist(board, y, x - 1, word, i + 1)      // left neighbor
                || exist(board, y + 1, x, word, i + 1)      // up neighbor
                || exist(board, y - 1, x, word, i + 1);     // down neighbor
        board[y][x] ^= 256;
        return exist;
    }
}
