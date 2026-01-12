package org.example.leetcode.findcheapestflight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {

    public static void main(final String[] args) {
        // adjacency list which represents the flights (src, dst, price)
        final int[][] flights = {
            { 0, 1, 100 },
            { 1, 2, 100 },
            { 0, 2, 500 }
        };
        // total nodes in graph
        final int n = 3;
        // maximum number of edges - 1
        final int k = 1;
        // starting node
        final int src = 0;
        // destination node
        final int dst = 2;
        final int result = new Solution().findCheapestPrice(n, flights, src, dst, k);
        System.out.println("Cheapest Price: " + result); // Expected output: 700
    }

    public int findCheapestPrice(
        final int n,
        final int[][] flights,
        final int src,
        final int dst,
        final int k
    ) {
        // make adjacency list
        final Map<Integer, List<int[]>> allAvailableOutboundFlights = createFlightsFromMap(flights, n);
        if (!allAvailableOutboundFlights.containsKey(src)) {
            return -1;
        }

        // represents min cost to get from src -> index
        final int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        // start bfs from src, price to get from src -> src is obviously 0
        final Queue<int[]> q = new LinkedList<>();
        final int[] start = new int[] { src, 0 };
        q.offer(start);
        int stops = 0;
        while (!q.isEmpty() && stops <= k) {
            final int size = q.size();
            for (int i = 0; i < size; ++i) {
                final int[] currFlight = q.poll();
                if (currFlight != null) {
                    final int destination = currFlight[0];
                    if (allAvailableOutboundFlights.containsKey(destination)) {
                        for (final int[] neighborFlight : allAvailableOutboundFlights.get(destination)) {
                            final int price = neighborFlight[1];
                            final int neighbourNode = neighborFlight[0];
                            // is it cheaper?
                            final int totalPrice = price + currFlight[1];
                            if (totalPrice >= minCost[neighbourNode])
                                continue;
                            minCost[neighbourNode] = totalPrice;
                            final int[] next = new int[] { neighbourNode, minCost[neighbourNode] };
                            q.offer(next);
                        }
                    }
                }
            }
            stops++;
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }


    // map is defined as: { source -> [ { destination, price } ]
    private Map<Integer, List<int[]>> createFlightsFromMap(final int[][] flights, final int n) {
        final Map<Integer, List<int[]>> map = new HashMap<>(n);
        for (final int[] flight: flights) {
            final int src = flight[0];
            if (!map.containsKey(src)) {
                map.put(src, new ArrayList<>());
            }
            final List<int[]> outboundFlights = map.get(src);
            outboundFlights.add(new int[] { flight[1], flight[2] });
        }
        return map;
    }
}
