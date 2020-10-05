package org.example.leetcode.coursescheduler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class App {
    public static void main(String[] args) {
        final int[][] prereqs = { { 1, 0 }, { 0, 1 } };
        final int numCourses = 2;
        final int[] courseList = new App().findOrder(numCourses, prereqs);
        System.out.println(Arrays.toString(courseList));
    }

    private boolean cycleDetected;

    enum State {
        UNVISITED,
        WILL_VISIT,
        VISITED
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        final Map<Integer, List<Integer>> adjacencyList = initAdjacencyList(prerequisites);
        final Map<Integer, State> visitedStates = initVisitedStates(numCourses);
        final Stack<Integer> topologicalSorting = new Stack<>();
        this.cycleDetected = false;
        for (int node = 0; node < numCourses; node++) {
            if (visitedStates.get(node) == State.UNVISITED) {
                dfs(node, topologicalSorting, adjacencyList, visitedStates);
            }
        }

        if (this.cycleDetected) {
            return new int[0];
        } else {
            final int[] ret = new int[numCourses];
            int i = 0;
            while (!topologicalSorting.empty()) {
                ret[i++] = topologicalSorting.pop();
            }
            return ret;
        }
    }

    private void dfs(
            final int node,
            final Stack<Integer> topologicalSorting,
            final Map<Integer, List<Integer>> adjacencyList,
            final Map<Integer, State> visitedStates
    ) {
        if (this.cycleDetected)
            return;

        visitedStates.put(node, State.WILL_VISIT);
        final List<Integer> neighbors = adjacencyList.getOrDefault(node, new LinkedList<>());

        for (int neighbor: neighbors) {
            final State neighborsVisitedState = visitedStates.get(neighbor);
            if (neighborsVisitedState == State.UNVISITED) {
                dfs(neighbor, topologicalSorting, adjacencyList, visitedStates);
            } else if (neighborsVisitedState == State.WILL_VISIT) {
                this.cycleDetected = true;
            }
        }

        topologicalSorting.push(node);
        visitedStates.put(node, State.VISITED);
    }

    private Map<Integer, List<Integer>> initAdjacencyList(final int[][] prerequisites) {
        final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] prerequisite: prerequisites) {
            final int src = prerequisite[1];
            final int dest = prerequisite[0];
            if (adjacencyList.containsKey(src)) {
                final List<Integer> adjacentNodes = adjacencyList.get(src);
                adjacentNodes.add(dest);
            } else {
                final List<Integer> adjacentNodes = new LinkedList<>();
                adjacentNodes.add(dest);
                adjacencyList.put(src, adjacentNodes);
            }
        }
        return adjacencyList;
    }

    private Map<Integer, State> initVisitedStates(final int numCourses) {
        final Map<Integer, State> visitedStates = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            visitedStates.put(i, State.UNVISITED);
        }
        return visitedStates;
    }
}
