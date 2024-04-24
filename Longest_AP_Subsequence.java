class Solution {
    public int solveMemo(int i, int diff, int[] nums, int[][] memo) {
        // Base case: If index i goes below 0, return 0
        if (i < 0)
            return 0;

        // Check if the result for the current state is already memoized
        if (memo[i][diff] != -1)
            return memo[i][diff];

        int ans = 0;
        // Iterate over previous elements to find the longest arithmetic sequence
        for (int k = i - 1; k >= 0; k--) {
            if ((nums[i] - nums[k] + 500) == diff) {
                ans = Math.max(ans, 1 + solveMemo(k, diff, nums, memo));
            }
        }

        // Memoize the result for the current state
        memo[i][diff] = ans;

        return ans;
    }

    public int solve(int[] nums) {
        int n = nums.length;

        // Base case: If the array has 2 or fewer elements, return its length
        if (n <= 2)
            return n;

        // Initialize a memoization table with -1
        int[][] memo = new int[n + 1][1001];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int ans = 0;

        // Iterate over all pairs of elements
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i] + 500;
                // Update the answer by finding the longest arithmetic sequence
                ans = Math.max(ans, 2 + solveMemo(i, diff, nums, memo));
            }
        }
        return ans;
    }

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;

        // Base case: If the array has 2 or fewer elements, return its length
        if (n <= 2)
            return n;

        // Initialize a memoization table with -1
        int[][] memo = new int[n][1001];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int ans = 0;

        // Iterate over all pairs of elements
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i] + 500;
                memo[i][diff] = Math.max(memo[i][diff], 2);
                memo[i][diff] = Math.max(memo[i][diff], 1 + memo[j][diff]);
                ans = Math.max(ans, memo[i][diff]);
            }
        }
        return ans;
    }
}
