package org.example.leetcode.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    private static final int ITERATIONS = 100000000;
    private static final double TOLERANCE = 0.05;


    public static void main(String[] args) {
        final int[] nums = {1, 2, 3, 4, 5};
        final Solution solution = new Solution();
        final double score = solution.evaluateShuffle(nums, solution::riffleShuffle, ITERATIONS);
        System.out.printf("score is %.6f%n", score);
        if (score > TOLERANCE) {
            System.out.println("shuffle is not uniform");
        } else {
            System.out.println("shuffle is uniform");
        }
    }

    /**
     * best shuffle implementation (Fisher-Yates shuffle)
     * O(n) time complexity
     * O(1) space complexity
     */
    public int[] shuffleInPlace(final int[] nums) {
        final Random rand = new Random();

        for (int i = (nums.length - 1); i > 0; i--) {
            final int j = rand.nextInt(i + 1);
            // Swap array[i] with array[j]
            final int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        return nums;
    }

    /**
     * Riffle shuffle implementation (like a deck of cards)
     * O(n) time complexity
     * O(n) space complexity - though it *can* be done in place
     */
    public int[] riffleShuffle(int[] nums) {
        int[] shuffled = new int[nums.length];
        final int mid = nums.length / 2;
        int l = 0, r = mid;
        int i = 0;
        while (l < mid && r < nums.length) {
            shuffled[i++] = nums[l++];
            shuffled[i++] = nums[r++];
        }

        // might result in one element being left out
        // it will always be right
        if (r < nums.length) {
            shuffled[i] = nums[r];
        }

        return shuffled;
    }

    /**
     * Naive shuffle implementation (old Fisher-Yates shuffle)
     * O(n^2) time complexity
     * O(n) space complexity
     */
    public int[] naiveShuffle(final int[] nums) {
        final List<Integer> unShuffled = Arrays.stream(nums).boxed().collect(Collectors.toList());
        final List<Integer> shuffled = new ArrayList<>();

        while(!unShuffled.isEmpty()) {
            // get a random index between 0 and unShuffled.size() - 1
            final Random r = new Random();
            final int randomIndex = r.nextInt(unShuffled.size());
            // add the element at that index to shuffled
            final int randomUnShuffledElement = unShuffled.get(randomIndex);
            shuffled.add(randomUnShuffledElement);
            // remove the element from unShuffled
            unShuffled.remove(randomIndex);
        }

        return shuffled.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Sorting shuffle implementation
     * O(n log n) time complexity
     * O(n) space complexity - from the random values + indices
     */
    public int[] sortingShuffle(final int[] nums) {
        // create an array of n values from 0 to 1
        final int n = nums.length;
        final Random r = new Random();
        final double[] randValues = new double[n];
        for (int i = 0; i < n; i++) {
            randValues[i] = r.nextDouble();
        }

        // next generate an array of n value from 0 to n-1
        final Integer[] randIndices = new Integer[n];
        for (int i = 0; i < n; i++) {
            randIndices[i] = i;
        }

        // sort the randIndices array based on the randValues
        Arrays.sort(randIndices, Comparator.comparingDouble(a -> randValues[a]));

        for (int i = 0; i < n; i++) {
            // swap the elements in nums based on the sorted randIndices
            final int temp = nums[i];
            nums[i] = nums[randIndices[i]];
            nums[randIndices[i]] = temp;
        }

        return nums;
    }

    public double evaluateShuffle(final int[] nums, final Function<int[], int[]> shuffleFunction, final int iterations) {
        final int[][] allPermutations = generateAllPermutations(nums);
        final Map<Integer, Integer> frequencyMap = new HashMap<>();

        // seed map
        for (final int[] permutation : allPermutations) {
            final int hash = Arrays.hashCode(permutation);
            frequencyMap.put(hash, 0);
        }

        // run iterations
        for (int i = 0; i < iterations; i++) {
            final int[] shuffled = shuffleFunction.apply(nums);
            final int hash = Arrays.hashCode(shuffled);
            final int count = frequencyMap.get(hash);
            final int inc = count + 1;
            frequencyMap.put(hash, inc);
        }

        // check uniform distribution using some kind of statistical test
        final int n = allPermutations.length;
        final double expectedFrequency = (double) iterations / n;
        final List<Double> diffs = frequencyMap.values().stream()
                .map(frequency -> ((int) Math.abs(frequency - expectedFrequency) / expectedFrequency))
                .collect(Collectors.toList());
        return diffs.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private int[][] generateAllPermutations(final int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);

        int[][] permutationsArray = new int[result.size()][nums.length];
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < nums.length; j++) {
                permutationsArray[i][j] = result.get(i).get(j);
            }
        }
        return permutationsArray;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, final int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    continue;
                }
                tempList.add(num);
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}