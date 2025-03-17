package org.example.leetcode.criticalconnections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));
        final List<List<Integer>> criticalConnections = new App().criticalConnections(connections.size(), connections);
        for (final List<Integer> criticalConnection : criticalConnections) {
            final String str = String.format("[%d - %d]", criticalConnection.get(0), criticalConnection.get(1));
            System.out.println(str);
        }
    }

    public List<List<Integer>> criticalConnections(final int n, final List<List<Integer>> connections) {
        // construct adjacency list from connections
        final Map<Integer, List<Integer>> adjacencyList = constructAdjacencyList(connections);
        final Set<List<Integer>> connectionsSet = new HashSet<>(connections);
        final int[] ranks = new int[n];
        Arrays.fill(ranks, Integer.MIN_VALUE);
        dfs(adjacencyList, 0, 0, ranks, connectionsSet);
        return new ArrayList<>(connectionsSet);
    }

    private Map<Integer, List<Integer>> constructAdjacencyList(final List<List<Integer>> connections) {
        final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (final List<Integer> connection : connections) {
            final int pa = connection.get(0);
            final int pb = connection.get(1);
            adjacencyList.putIfAbsent(pa, new ArrayList<>());
            adjacencyList.get(pa).add(pb);
            adjacencyList.putIfAbsent(pb, new ArrayList<>());
            adjacencyList.get(pb).add(pa);
        }
        return adjacencyList;
    }

    private int dfs(
        final Map<Integer, List<Integer>> adjacencyList,
        final int node,
        final int depth,
        final int[] ranks,
        final Set<List<Integer>> connectionsSet
    ) {
        if (ranks[node] >= 0)
            return ranks[node]; // already visited node. return its rank
        ranks[node] = depth;
        int minDepthFound = depth;
        for (final int neighbor : adjacencyList.get(node)) {
            if (ranks[neighbor] == depth - 1)// ignore parent
                continue;
            final int minDepth = dfs(adjacencyList, neighbor, depth + 1, ranks, connectionsSet);
            minDepthFound = Math.min(minDepthFound, minDepth);
            if (minDepth <= depth) {
                // to avoid the sorting just try to remove both combinations. of (x,y) and (y,x)
                connectionsSet.remove(Arrays.asList(node, neighbor));
                connectionsSet.remove(Arrays.asList(neighbor, node));
            }
        }
        return minDepthFound;
    }
}
