import java.util.Arrays;

public class Solution {
    public static int solveRec(int n, int x, int y, int z){
        if(n == 0)
            return 0;
        if(n < 0)
            return Integer.MIN_VALUE;
        
        int a = solveRec(n-x, x, y, z) + 1;
        int b = solveRec(n-y, x, y, z) + 1;
        int c = solveRec(n-z, x, y, z) + 1;

        int ans = Math.max(a, Math.max(b, c));

        return ans;
    }

    public static int solveMemo(int n, int x, int y, int z, int[] dp){
        if(n == 0)
            return 0;
        if(n < 0)
            return Integer.MIN_VALUE;
        
        if(dp[n] != -1)
            return dp[n];

        int a = solveMemo(n-x, x, y, z, dp) + 1;
        int b = solveMemo(n-y, x, y, z, dp) + 1;
        int c = solveMemo(n-z, x, y, z, dp) + 1;

        dp[n] = Math.max(a, Math.max(b, c));

        return dp[n];
    }

    public static int solveTab(int n, int x, int y, int z){
        int[] dp = new int[n+1];

        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            if((i-x) >= 0)
                dp[i] = Math.max(dp[i], dp[i-x]+1);
            if((i-y) >= 0)
                dp[i] = Math.max(dp[i], dp[i-y]+1);
            if((i-z) >= 0)
                dp[i] = Math.max(dp[i], dp[i-z]+1);
        }

        return (dp[n] < 0)? 0:dp[n];
    }
    
    public static int cutSegments(int n, int x, int y, int z) {
        // Write your code here.
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // int ans =  solveMemo(n, x, y, z, dp);
        // return (ans < 0 )? 0: ans;

        return solveTab(n, x, y, z);
    }
}
