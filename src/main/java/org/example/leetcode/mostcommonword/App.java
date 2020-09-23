package org.example.leetcode.mostcommonword;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final String paragraph = "Bob";
        final String[] banned = new String[] { };

        System.out.println(new App().mostCommonWord(paragraph, banned));
    }

    public String mostCommonWord(final String paragraph, final String[] banned) {
        final Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        final Map<String, Integer> safeWordFrequencies = new HashMap<>();
        String currentMostFrequentWord = null;
        int currMaxHits = Integer.MIN_VALUE;
        int index = 0;

        do {
            final String word = getNextWord(index, paragraph).toLowerCase();
            if (word.length() > 0 && !bannedSet.contains(word)) {
                int newWordFrequency = safeWordFrequencies.getOrDefault(word, 0) + 1;
                safeWordFrequencies.put(word, newWordFrequency);
                if (newWordFrequency > currMaxHits) {
                    currMaxHits = newWordFrequency;
                    currentMostFrequentWord = word;
                }
            }
            index += (word.length() + 1);
        } while (index < paragraph.length());

        return currentMostFrequentWord;
    }

    private String getNextWord(final int start, final String paragraph) {
        int stop = start;
        while (stop < paragraph.length() && Character.isAlphabetic(paragraph.charAt(stop)))
            stop++;
        return paragraph.substring(start, stop);
    }
}
