package org.example.leetcode.wordsearch2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class App {
    static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>(26);
        private String word = null;
    }

    private static final int[][] shift = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<String> findWords(char[][] board, String[] words) {
        // step 1. construct the Trie
        final TrieNode root = constructTrie(words);

        final List<String> result = new ArrayList<>();

        // step 2. backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row)
            for (int col = 0; col < board[row].length; ++col)
                if (root.children.containsKey(board[row][col]))
                    backtracking(row, col, root, board, result);

        return result;
    }

    private TrieNode constructTrie(final String[] words) {
        final TrieNode root = new TrieNode();

        for (final String word : words) {

            TrieNode node = root;

            for (final char letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }

            node.word = word;  // store words in Trie
        }

        return root;
    }

    private void backtracking(
        final int row,
        final int col,
        final TrieNode parent,
        final char[][] board,
        final List<String> result
    ) {
        final char letter = board[row][col];
        final TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        for (final int[] delta : shift) {
            final int nr = (row + delta[0]), nc = (col + delta[1]);
            final boolean onBoard = (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length);
            if (onBoard && currNode.children.containsKey(board[nr][nc]))
                backtracking(nr, nc, currNode, board, result);
        }

        // End of EXPLORATION, restore the original letter in the board.
        board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty())
            parent.children.remove(letter);
    }
}
