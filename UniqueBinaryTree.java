class Solution {
    public int solveRec(int n) {
        if (n <= 1)
            return 1;

        // run a for loop to make each node as root node
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += solveRec(i - 1) * solveRec(n - i);
        }

        return ans;
    }

    public int solveMemo(int n, int[] dp) {
        if (n <= 1)
            return 1;

        if (dp[n] != -1)
            return dp[n];
        // run a for loop to make each node as root node
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            
        }

        return dp[n] = ans;
    }

    public int solveTab(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        // we find combination from 2 to n now
        // i denote total number of node in current so we find combination from 1 to i in each iteration
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public void solve(int n) {
        // return solveRec(n);
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // return solveMemo(n, dp);
    }

    public int numTrees(int n) {
        return solveTab(n);
    }
}
