package org.example.leetcode.justify;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        final String[] words = {
            "ask", "not", "what",
            "your", "country", "can",
            "do", "for", "you", "ask",
            "what", "you", "can", "do",
            "for", "your", "country"
        };
        final int maxWidth = 16;
        final List<String> justifiedLines = new App().fullJustify(words, maxWidth);
        justifiedLines.forEach(System.out::println);
    }

    public List<String> fullJustify(final String[] words, final int maxWidth) {
        int left = 0;
        final List<String> result = new ArrayList<>();

        while (left < words.length) {
            final int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    private int findRight(final int left, final String[] words, final int maxWidth) {
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth)
            sum += (1 + words[right++].length());

        return (right - 1);
    }

    private String justify(final int left, final int right, final String[] words, final int maxWidth) {
        if (right - left == 0)
            return padResult(words[left], maxWidth);

        final boolean isLastLine = (right == words.length - 1);
        final int numSpaces = (right - left);
        final int totalSpace = (maxWidth - wordsLength(left, right, words));

        final String space = (isLastLine) ? " " : blank(totalSpace / numSpaces);
        int remainder = (isLastLine) ? 0 : (totalSpace % numSpaces);

        final StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                .append(space)
                .append((remainder-- > 0) ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    private int wordsLength(final int left, final int right, final String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++)
            wordsLength += words[i].length();
        return wordsLength;
    }

    private String padResult(final String result, final int maxWidth) {
        return (result + blank(maxWidth - result.length()));
    }

    private String blank(final int length) {
        return new String(new char[length]).replace('\0', ' ');
    }
}
