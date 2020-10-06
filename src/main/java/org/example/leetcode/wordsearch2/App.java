package org.example.leetcode.wordsearch2;

import java.util.ArrayList;
import java.util.List;

public class App {

    private static class TrieNode {
        private final char value;
        private final TrieNode[] neighbors = new TrieNode[26];
        private TrieNode(final char value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        final char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        final String[] words = {"oath", "pea", "eat", "rain"};
        final List<String> foundWords = new App().findWords(board, words);
        foundWords.forEach(System.out::println);
    }
    
    public List<String> findWords(final char[][] board, final String[] words) {
        final TrieNode[] trie = constructTrie(words);
        return new ArrayList<>();
    }

    private TrieNode[] constructTrie(final String[] words) {
        final TrieNode[] result = new TrieNode[26];
        for (final String word : words) {
            final int n = word.length();
            TrieNode node;
            for (int i = 0; i < n; ++i) {
                final char c = word.charAt(i);
                final int charIndex = (c - 97);
                if ()
            }
        }
        return result;
    }
}
