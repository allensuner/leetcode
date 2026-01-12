package org.example.leetcode.trappingwater2d;

public class App {

    public static void main(final String[] args) {
        final int[] map = { 2, 0, 2 };
        System.out.println(new App().trap(map));
    }

    public int trap(final int[] height) {
        final int size = height.length;
        if (size < 3)
            return 0;

        int water = 0;

        int left = 0,
            right = size - 1,
            leftMax = height[left],
            rightMax = height[right];

        while (left < right) {

            final int leftLevel = height[left],
                      rightLevel = height[right];

            if (leftLevel < rightLevel) {
                if (leftLevel >= leftMax)
                    leftMax = leftLevel;
                else
                    water += (leftMax - leftLevel);
                ++left;
            } else {
                if (rightLevel >= rightMax)
                    rightMax = rightLevel;
                else
                    water += (rightMax - rightLevel);
                --right;
            }
        }

        return water;
    }
}
