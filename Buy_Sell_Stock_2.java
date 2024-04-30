class Solution {
    public int solveRec(int[] prices, int index, int n, boolean buy) {
        if (index == n)
            return 0;

        int profit = 0;
        if (buy) {
            int incl = -prices[index] + solveRec(prices, index + 1, n, false);
            int excl = solveRec(prices, index + 1, n, true);
            profit = Math.max(incl, excl);
        } else {
            int incl = prices[index] + solveRec(prices, index + 1, n, true);
            int excl = solveRec(prices, index + 1, n, false);
            profit = Math.max(incl, excl);
        }
        return profit;
    }

    public int solveMemo(int[] prices, int index, int n, int buy, int[][] dp) {
        if (index == n)
            return 0;

        if (dp[index][buy] != -1)
            return dp[index][buy];

        int profit = 0;
        if (buy == 1) {
            int incl = -prices[index] + solveMemo(prices, index + 1, n, 0, dp);
            int excl = solveMemo(prices, index + 1, n, 1, dp);
            profit = Math.max(incl, excl);
        } else {
            int incl = prices[index] + solveMemo(prices, index + 1, n, 1, dp);
            int excl = solveMemo(prices, index + 1, n, 0, dp);
            profit = Math.max(incl, excl);
        }

        dp[index][buy] = profit;

        return profit;
    }

    public int solveMemo(int[] prices) {
        int n = prices.length;

        int[] curr = new int[2];
        int[] next = new int[2];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 1) {
                    int incl = -prices[index] + next[0];
                    int excl = next[1];
                    profit = Math.max(incl, excl);
                } else {
                    int incl = prices[index] + next[1];
                    int excl = next[0];
                    profit = Math.max(incl, excl);
                }

                curr[buy] = profit;
            }
            next = curr;
        }
        return next[1];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        int currNotBuy = 0;
        int currBuy = 0;

        int nextNotBuy = 0;
        int nextBuy = 0;

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 1) {
                    int incl = -prices[index] + nextNotBuy;
                    int excl = nextBuy;
                    profit = Math.max(incl, excl);
                    currBuy = profit;
                } else {
                    int incl = prices[index] + nextBuy;
                    int excl = nextNotBuy;
                    profit = Math.max(incl, excl);
                    currNotBuy = profit;
                }

                currBuy = profit;
            }
            nextBuy = currBuy;
            nextNotBuy = currNotBuy;
        }
        
        return currBuy;
    }
}
