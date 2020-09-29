package org.example.leetcode.itinerary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class App {

    private static final Map<String, PriorityQueue<String>> flights;
    private static final Map<String, boolean[]> visited;
    private static List<String> result;

    static {
        flights = new HashMap<>();
        visited = new HashMap<>();
        result = new ArrayList<>();
    }

    /*
        Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                     But it is larger in lexical order.
     */
    // [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    public static void main(String[] args) {
        final List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        new App().findItinerary(tickets).forEach(System.out::println);
    }

    public List<String> findItinerary(final List<List<String>> tickets) {

        // step 1. create the graph
        tickets.forEach(ticket -> {
            final String source = ticket.get(0), destination = ticket.get(1);
            flights.putIfAbsent(source, new PriorityQueue<>(Comparator.naturalOrder()));
            final PriorityQueue<String> destinations = flights.get(source);
            destinations.offer(destination);
        });

        flights.forEach((departure, arrivals) -> visited.putIfAbsent(departure, new boolean[flights.get(departure).size()]));

        final LinkedList<String> route = new LinkedList<>();
        final String start = "JFK";
        route.add(start);

        // step 2. start the backtracking
        backtrack(start, tickets.size(), route);
        return result;
    }

    /*
        As the final step, we kick off the backtracking traversal on the above graph, to obtain the final result.
        At the beginning of the backtracking function, as the bottom case, we check if we have already obtained a valid itinerary.
        Otherwise, we enumerate the next destinations in order.
        We mark the status of visit, before and after each backtracking loop.
     */
    private boolean backtrack(
        final String origin,
        final int numFlights,
        final LinkedList<String> route
    ) {
        if (route.size() == (numFlights + 1)) {
            result = new ArrayList<>(route);
            return true;
        }

        if (!flights.containsKey(origin))
            return false;

        int i = 0;
        final boolean[] visitedForOrigin = visited.get(origin);

        for (final String dest : flights.get(origin)) {
            if (!visitedForOrigin[i]) {
                visitedForOrigin[i] = true;
                route.add(dest);
                final boolean ret = backtrack(dest, numFlights, route);
                route.pollLast();
                visitedForOrigin[i] = false;
                if (ret) return true;
            }
            ++i;
        }

        return false;
    }
}
