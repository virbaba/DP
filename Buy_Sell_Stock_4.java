class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k+1];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int transaction = 1; transaction <= k; transaction++) {
                    int profit = 0;
                    if (buy == 1) {
                        int incl = -prices[index] + dp[index + 1][0][transaction];
                        int excl = dp[index + 1][1][transaction];
                        profit = Math.max(incl, excl);
                    } else {
                        int incl = prices[index] + dp[index + 1][1][transaction - 1];
                        int excl = dp[index + 1][0][transaction];
                        profit = Math.max(incl, excl);
                    }

                    dp[index][buy][transaction] = profit;
                }
            }
        }
        return dp[0][1][k];
    }
}
