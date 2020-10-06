package org.example.leetcode.perfectnumber;

public class App {
    public static void main(String[] args) {
        final int num = 28;
        System.out.println(new App().checkPerfectNumber(num));
    }

    public boolean checkPerfectNumber(final int num) {
        if (num <= 0) return false;
        int sum = 0;
        for (int i = 1; (i * i) <= num; ++i) {
            if ((num % i) == 0) {
                sum += i;
                if ((i * i) != num)
                    sum += (num / i);
            }
        }
        return ((sum - num) == num);
    }
}
