class Solution {
    public int solveRec(int dice, int face, int target) {
        if (target < 0 || (dice == 0 && target > 0) || (dice > 0 && target == 0))
            return 0;

        if (dice == 0 && target == 0)
            return 1;

        int ans = 0;
        for (int i = 1; i <= face; i++) {
            ans += solveRec(dice - 1, face, target - i);
        }
        return ans;
    }

    public int solveMemo(int dice, int face, int target, int[][] dp) {
        if (target < 0 || (dice == 0 && target > 0) || (dice > 0 && target == 0))
            return 0;
        if (dice == 0 && target == 0)
            return 1;

        if (dp[dice][target] != -1)
            return dp[dice][target];

        int ans = 0;
        for (int i = 1; i <= face; i++) {
            ans = ans % 1000000007 + solveMemo(dice - 1, face, target - i, dp) % 1000000007;
        }
        return dp[dice][target] = ans % 1000000007;
    }

    public int solveTab(int dice, int face, int target) {
        int[][] dp = new int[dice + 1][target + 1];
        dp[0][0] = 1;

        for (int d = 1; d <= dice; d++) {
            for (int t = 1; t <= target; t++) {
                for (int i = 1; i <= face; i++) {
                    if (t - i >= 0) {
                        dp[d][t] = (dp[d][t] + dp[d - 1][t - i]) % 1000000007;
                    }
                }
            }
        }
        return dp[dice][target];
    }

    public int numRollsToTarget(int dice, int face, int target) {
        return solveTab(dice, face, target);
    }
}
