class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;

        // 2D array to store maximum values from index i to j
        int[][] max = new int[n][n];

        // Initialize the 2D array
        for (int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                max[i][j] = Math.max(arr[j], max[i][j - 1]);
            }
        }

        // Print the 2D array
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.println("Max value from index " + i + " to " + j + " is: " + max[i][j]);
            }
        }

        // 2D array to memoize minimum costs
        Integer[][] dp = new Integer[n][n];

        // Recursive function to find minimum cost
        return findMinCost(arr, 0, n - 1, max, dp);
    }

    private static int findMinCost(int[] arr, int start, int end, int[][] max, Integer[][] dp) {
        // Base case: if the subarray has only one element, cost is 0
        if (start == end) {
            return 0;
        }

        // If result is already computed, return it
        if (dp[start][end] != null) {
            return dp[start][end];
        }

        int minCost = Integer.MAX_VALUE;

        // Try all possible partitions
        for (int k = start; k < end; k++) {
            int leftCost = findMinCost(arr, start, k, max, dp);
            int rightCost = findMinCost(arr, k + 1, end, max, dp);
            int cost = leftCost + rightCost + max[start][k] * max[k + 1][end];
            minCost = Math.min(minCost, cost);
        }

        // Memoize the result
        dp[start][end] = minCost;

        return minCost;
    }
}
