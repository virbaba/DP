class Solution {
    public int solveRec(int[] prices, int index, int n, int buy, int transaction, int[][][] dp){
        if(index == n || transaction == 0)
            return 0;
        
        if(dp[index][buy][transaction] != -1)
            return dp[index][buy][transaction];
        
        int profit = 0;
        if(buy == 1){
            int incl = -prices[index] + solveRec(prices, index + 1, n, 0, transaction, dp);
            int excl = solveRec(prices, index + 1, n , 1, transaction, dp);
            profit = Math.max(incl, excl);

        }else{
            int incl = prices[index] + solveRec(prices, index + 1, n, 1, transaction - 1, dp);
            int excl = solveRec(prices, index + 1, n , 0, transaction, dp);
            profit = Math.max(incl, excl);
        }

        return dp[index][buy][transaction] = profit;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int transaction = 1; transaction <= 2; transaction++) {
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
        return dp[0][1][2];
    }
}
