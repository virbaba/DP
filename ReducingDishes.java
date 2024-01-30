import java.util.*;
class Solution {
    public static int solveRec(int[] satisfaction, int i, int n, int time){
        if(i == n)
            return 0;
        
        int incl = (satisfaction[i] * time) + solveRec(satisfaction, i + 1, n, time + 1);
        int excl = solveRec(satisfaction, i + 1, n, time);

        return Math.max(incl, excl);
    }
    public static int solveMemo(int[] satisfaction, int i, int n, int time, int[][] dp){
        if(i == n)
            return 0;
        
        if(dp[i][time] != -1)
            return dp[i][time];

        int incl = (satisfaction[i] * time) + solveMemo(satisfaction, i + 1, n, time + 1, dp);
        int excl = solveMemo(satisfaction, i + 1, n, time, dp);

        dp[i][time] =  Math.max(incl, excl);
        return dp[i][time];
    }

    public static int solveTab(int[] satisfaction){
        int n = satisfaction.length;
        int[][] dp = new int[n + 1][n + 1];

        for(int i = n - 1; i >= 0; i--){
            for(int time = i; time >= 0; time--){
                int incl = (satisfaction[i] * (time+1)) + dp[i + 1][time + 1];
                int excl = dp[i + 1][time];
                dp[i][time] =  Math.max(incl, excl);
            }
        }
        return dp[0][0];
    }
    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        int time = 1;
        
        Arrays.sort(satisfaction);
        return solveTab(satisfaction);
    }
}
