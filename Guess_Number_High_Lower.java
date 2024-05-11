class Solution {
    public int solveRec(int start, int n){
        if(start >= n)
            return 0;

        int ans = Integer.MAX_VALUE;
        for(int i = start; i <= n; i++){
            ans = Math.min(ans, i + Math.max(solveRec(start, i - 1), solveRec(i + 1, n)));
        }
        return ans;
    }

    public int solveMemo(int start, int n, int[][] dp){
        if(start >= n)
            return 0;

        if(dp[start][n] != -1)
            return dp[start][n];
        
        int ans = Integer.MAX_VALUE;
        for(int i = start; i <= n; i++){
            ans = Math.min(ans, i + Math.max(solveMemo(start, i - 1, dp), solveMemo(i + 1, n, dp)));
        }
        return dp[start][n] = ans;
    }

    public void solve(int n){
        // return solveRec(1, n);
        // int[][] dp = new int[n + 1][n + 1];

        // for(int[] arr : dp)
        //     Arrays.fill(arr, - 1);
        
        // return solveMemo(1, n, dp);
    }

    public int getMoneyAmount(int n) {
         
    }
}
