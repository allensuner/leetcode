package org.example.leetcode.kclosestpoints;

import java.util.Arrays;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) {
        final int[][] points = new int[][] {
            { 1, 3 },
            { -2, 2 }
        };
        final int k = 1;
        System.out.println(Arrays.deepToString(new App().kClosest(points, k)));
    }

    public int[][] kClosest(final int[][] points, final int K) {
        final int[][] res = new int[K][2];
        final PriorityQueue<int[]> closest = new PriorityQueue<>((pointA, pointB) -> {
            final double aDistance = distanceFromOrigin(pointA);
            final double bDistance = distanceFromOrigin(pointB);
            if (aDistance > bDistance) return -1;
            else if (bDistance > aDistance) return 1;
            return 0;
        });

        for (final int[] point : points) {
            if (closest.size() < K) {
                closest.offer(point);
            } else {
                final int[] longestOfTheClosest = closest.peek();
                if ((longestOfTheClosest != null) && (distanceFromOrigin(point) < distanceFromOrigin(longestOfTheClosest))) {
                    closest.poll();
                    closest.offer(point);
                }
            }
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = closest.poll();
        }
        return res;
    }

    private double distanceFromOrigin(final int[] point) {
        final int x = point[0], y = point[1];
        final int hypotenuseSquared = (Math.abs(x) * Math.abs(x)) + (Math.abs(y) * Math.abs(y));
        return Math.sqrt(hypotenuseSquared);
    }
}
