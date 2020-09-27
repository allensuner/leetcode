package org.example.leetcode.calculator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class App {

    private static final Map<Character, Integer> precedence = new HashMap<>();
    private static final Set<Character> validOperators = new HashSet<>(Arrays.asList('+', '-', '*', '/'));

    static {
        precedence.put('(', -1);
        precedence.put('+', 0);
        precedence.put('-', 0);
        precedence.put('/', 1);
        precedence.put('*', 1);
    }

    public static void main(String[] args) {
        final String expression = "(2+6* 3+5- (3*14/7+2)*5)+3";
        System.out.println(new App().calculate(expression));
    }

    public int calculate(String s) {
        final Stack<Integer> operands = new Stack<>();
        final Stack<Character> operators = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // iterate character by character
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // if the character is a digit then "tokenize" the number and push it on to the operand stack
                int num = c - '0';
                while (((i + 1) < n) && (Character.isDigit(s.charAt(i + 1)))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }
                operands.push(num);
            } else if (c == '(') {
                // if open paren simply push it onto the operator stack
                operators.push(c);
            } else if (c == ')') {
                // if close paren evaluate the subexpression created by the closing paren
                // go until you find the matching open paren
                while (operators.peek() != '(') {
                    final int subExpressionValue = operate(operands, operators);
                    operands.push(subExpressionValue);
                }
                // then pop it off the operator stack
                operators.pop();
            } else if (validOperators.contains(c)) {
                // if it's an operator that's not in a subexpression it's time to operate
                // continually operate until you reach an operator with higher precedence OR the operator stack is empty
                while (!operators.isEmpty() && determinePrecedence(c, operators.peek()) <= 0) {
                    operands.push(operate(operands, operators));
                }
                // then once you're done operating, just push the operator onto the stack
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            operands.push(operate(operands, operators));
        }

        return operands.pop();
    }

    private int determinePrecedence(final char operatorA, final char operatorB) {
        return precedence.get(operatorA) - precedence.get(operatorB);
    }

    private int operate(final Stack<Integer> operands, final Stack<Character> operators) {
        int a = operands.pop();
        int b = operands.pop();
        char c = operators.pop();

        switch(c) {
            case '+': return (a + b);
            case '-': return (b - a);
            case '*': return (a * b);
            case '/': return (b / a);
            default: return 0;
        }
    }
}