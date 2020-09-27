package org.example.leetcode.justify;

import java.util.ArrayList;
import java.util.Arrays;
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
        final App a = new App();
        final List<String> justifiedLines = a.fullJustify(words, maxWidth);
        justifiedLines.forEach(System.out::println);
    }

    public List<String> fullJustify(final String[] words, final int maxWidth) {
        final List<String> lines = new ArrayList<>();
        int lineStart = 0;
        for (int i = 0; i < words.length; ++i) {
            final String[] currentLine = Arrays.copyOfRange(words, lineStart, i + 1);
            final int lineLength = getLineLength(currentLine);
            if (lineLength > maxWidth) {
                final String line = fillLineWithSpacesJustified(Arrays.copyOfRange(words, lineStart, i), maxWidth);
                lines.add(line);
                lineStart = i;
            }
        }
        final String[] lastWords = Arrays.copyOfRange(words, lineStart, words.length);
        lines.add(fillLineWithSpacesLeftAligned(lastWords, maxWidth));
        return lines;
    }

    private String fillLineWithSpacesLeftAligned(final String[] wordsOnLine, final int maxWidth) {
        final StringBuilder sb = new StringBuilder(String.join(" ", wordsOnLine));
        while (sb.length() < maxWidth)
            sb.append(' ');
        return sb.toString();
    }

    private String fillLineWithSpacesJustified(final String[] wordsOnLine, final int maxWidth) {
        final int letterLength = Arrays.stream(wordsOnLine).mapToInt(String::length).sum();
        int totalSpaceLength = maxWidth - letterLength;
        int numSpaces = wordsOnLine.length - 1;
        if (numSpaces == 0) {
            final StringBuilder sb = new StringBuilder(wordsOnLine[0]);
            while (sb.length() < maxWidth)
                sb.append(' ');
            return sb.toString();
        }

        final int[] spaceLengths = new int[numSpaces];
        final String[] spaces = new String[numSpaces];

        //start at total length = k
        //while k % sections != 0
        //    k++;
        //maxLength = k / sections;
        //maxLength will go for (total - sections) times
        //go again, treating (total - sections) as maxLength and (total - sections) as sections
        int x = 0;
        while (x < spaceLengths.length) {
            int k = totalSpaceLength;
            while ((k % numSpaces) != 0)
                ++k;
            final int maxSpaceLength = (k / numSpaces);
            final int n = Math.min((totalSpaceLength - numSpaces), numSpaces);
            for (int i = 0; i < n; ++i)
                spaceLengths[x++] = maxSpaceLength;
            totalSpaceLength = (totalSpaceLength - numSpaces);
            numSpaces = totalSpaceLength;
        }

        for (int i = 0; i < spaces.length; ++i)
            spaces[i] = new String(new char[spaceLengths[i]]).replace('\0', ' ');

        final String[] lineArray = new String[numSpaces + wordsOnLine.length];
        int j = 0;
        for (int i = 0; i < wordsOnLine.length; i++) {
            lineArray[j++] = wordsOnLine[i];
            if (i < spaces.length)
                lineArray[j++] = spaces[i];
        }

        return String.join("", lineArray);
    }

    private int getLineLength(final String[] wordsOnLine) {
        final int letterLength = Arrays.stream(wordsOnLine).mapToInt(String::length).sum();
        final int spaceLength = wordsOnLine.length - 1;
        return (letterLength + spaceLength);
    }
}
