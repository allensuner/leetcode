package org.example.leetcode.reorderlogs;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        final String[] logs = new String[] {
            "dig1 8 1 5 1",
            "let1 art can",
            "dig2 3 6",
            "let2 own kit dig",
            "let3 art zero"
        };
        final String[] sortedLogs = new App().reorderLogFiles(logs);
        System.out.println(Arrays.toString(sortedLogs));
    }

    // Reorder the logs so that all of the letter-logs come before any digit-log.
    // The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
    // The digit-logs should be put in their original order.
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (rawA, rawB) -> {
            final String a = rawA.substring(rawA.indexOf(' ') + 1);
            final String b = rawB.substring(rawB.indexOf(' ') + 1);
            final boolean aDigit = isDigitLog(a);
            final boolean bDigit = isDigitLog(b);

            // if both are letter logs
            if (!aDigit && !bDigit) {
                int logComp = a.compareTo(b);
                if (logComp == 0) {
                    logComp = rawA.compareTo(rawB);
                }
                return logComp;
            }

            // if one is a letter log
            // and the other is a digit log
            if (!aDigit) return -1;
            if (!bDigit) return 1;

            // if both are digit logs
            return 0;
        });
        return logs;
    }

    private boolean isDigitLog(final String log) {
        final int logStart = log.indexOf(' ') + 1;
        final String actualLog = log.substring(logStart);
        return Character.isDigit(actualLog.charAt(0));
    }
}
