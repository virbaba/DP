public class Solution {
    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.
        int[][] dp = new int[n + 1][2];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                    int profit = 0;
                    if (buy == 1) {
                        int incl = -prices[index] + dp[index + 1][0];
                        int excl = dp[index + 1][1];
                        profit = Math.max(incl, excl);
                    } else {
                        int incl = prices[index] + dp[index + 1][1] - fee;
                        int excl = dp[index + 1][0];
                        profit = Math.max(incl, excl);
                    }

                    dp[index][buy] = profit;
                }
            
        }
        return dp[0][1];
    }
}
