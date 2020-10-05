package org.example.leetcode.minimumheighttrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        printResult(new App().findMinHeightTrees(4, new int[][] {{1,0},{1,2},{1,3}})); // [1]
        printResult(new App().findMinHeightTrees(6, new int[][] {{3,0},{3,1},{3,2},{3,4},{5,4}})); // [3, 4]
        printResult(new App().findMinHeightTrees(1, new int[][] {})); // [0]
        printResult(new App().findMinHeightTrees(2, new int[][] {{1,0}})); // [1, 0]
    }

    private static void printResult(final List<Integer> result) {
        final String msg = result.stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.printf("[%s]%n", msg);
    }

    public List<Integer> findMinHeightTrees(final int n, final int[][] edges) {
        // construct an adjacency list
        final Map<Integer, List<Integer>> adjList = createAdjacencyList(n, edges);
        final List<Integer> result = new LinkedList<>();
        int currentMin = Integer.MAX_VALUE;
        for (int node = 0; node < n; ++node) {
            final int treeHeight = getTreeHeight(node, adjList);
            if (treeHeight < currentMin) {
                result.clear();
                result.add(node);
                currentMin = treeHeight;
            } else if (treeHeight == currentMin) {
                result.add(node);
            }
        }
        return result;
    }

    private int getTreeHeight(final int start, final Map<Integer, List<Integer>> adjList) {
        int level = 0;
        final Set<Integer> visitedNodes = new HashSet<>();
        final Queue<Integer> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(start);

        while (!nodesToVisit.isEmpty()) {
            final List<Integer> nodesAtLevel = new LinkedList<>();
            while (!nodesToVisit.isEmpty()) {
                final int node = nodesToVisit.poll();
                visitedNodes.add(node);
                nodesAtLevel.addAll(adjList.getOrDefault(node, new LinkedList<>()));
            }
            nodesAtLevel.forEach(node -> {
                if (!visitedNodes.contains(node))
                    nodesToVisit.offer(node);
            });
            level++;
        }

        return level;
    }

    private Map<Integer, List<Integer>> createAdjacencyList(final int n, final int[][] edges) {
        final Map<Integer, List<Integer>> adjList = new HashMap<>(n);
        for (final int[] edge : edges) {
            final int a = edge[0], b = edge[1];
            adjList.putIfAbsent(a, new ArrayList<>());
            adjList.putIfAbsent(b, new ArrayList<>());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        return adjList;
    }
}
