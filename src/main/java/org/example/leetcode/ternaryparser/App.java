package org.example.leetcode.ternaryparser;

import java.util.Stack;

public class App {
    /*
        T?T?F:5:3     ->
        (T?(T?F:5):3) ->
        (T?(F):3)     ->
        (T?F:3)       ->
        (F)           ->
        F

        F?1:T?4:5     ->
        (F?1:(T?4:5)) ->
        (F?1:(4))     ->
        (F?1:4)       ->
        (4)           ->
        4
     */

    /*
        Iterate the expression from tail,
        whenever encounter a character before '?',
        calculate the right value and push back to stack.
     */
    public static void main(String[] args) {
        final String expression = "F?1:T?4:5";
        System.out.println(new App().parseTernary(expression));
    }

    public String parseTernary(final String expression) {
        final Stack<Character> s = new Stack<>();
        final int lastIndex = expression.length() - 1;
        for (int i = lastIndex; i >= 0; --i) {
            final char c = expression.charAt(i);
            final boolean timeToEvaluate = (!s.isEmpty() && s.peek() == '?');
            if (timeToEvaluate) {
                s.pop(); // pop the '?'
                char result;
                if (c == 'T') {
                    // if it's truthy the result will be on the top
                    result = s.pop();
                    s.pop();
                    s.pop();
                } else {
                    // if it's falsy the result will be three deep
                    s.pop();
                    s.pop();
                    result = s.pop();
                }
                s.push(result);
            } else {
                s.push(c);
            }
        }
        return String.valueOf(s.pop());
    }
}
