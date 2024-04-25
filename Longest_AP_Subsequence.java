class Solution {
    public int solveRec(int[] nums, int i, int n, int diff) {
        if (i < 0)
            return 0;

        int ans = 0;
        for (int k = i - 1; k >= 0; k--) {
            if (nums[i] - nums[k] == diff)
                ans = Math.max(ans, 1 + solveRec(nums, k, n, diff));
        }
        return ans;

    }

    public int solveMemo(int[] nums, int i, int n, int diff, int[][] dp) {
        if (i < 0)
            return 0;

        if (dp[i][diff] != -1)
            return dp[i][diff];

        int ans = 0;
        for (int k = i - 1; k >= 0; k--) {
            if ((nums[i] - nums[k] + 500) == diff)
                ans = Math.max(ans, 1 + solveMemo(nums, k, n, diff, dp));
        }
        return dp[i][diff] = ans;

    }

    public int solve(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return n;

        int[][] dp = new int[n + 1][1001];
        for (int[] arr : dp)
            Arrays.fill(arr, -1);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i] + 500;
                ans = Math.max(ans, 2 + solveMemo(nums, i, n, diff, dp));
            }
        }

        return ans;
    }

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n + 1][1001];

        int ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i] + 500;
                dp[i][diff] = Math.max(dp[i][diff], 1 + dp[j][diff]);
                ans =  Math.max(ans, 1 + dp[i][diff]);
            }
        }

        return ans;
    }
}
