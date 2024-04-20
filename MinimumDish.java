class Solution {
    public static int solveRec(int[] satisfaction, int index, int n, int time){
        if(index >= n)
            return 0;
        
        int incl = satisfaction[index] * time + solveRec(satisfaction, index + 1, n, time + 1);
        int excl = solveRec(satisfaction, index + 1, n, time);

        return Math.max(incl, excl);
    }

    public static int solveMemo(int[] satisfaction, int index, int n, int time, int[][] dp){
        if(index >= n)
            return 0;
        
        if(dp[index][time] != -1)
            return dp[index][time];

        int incl = satisfaction[index] * time + solveMemo(satisfaction, index + 1, n, time + 1,dp);
        int excl = solveMemo(satisfaction, index + 1, n, time, dp);

        return dp[index][time] = Math.max(incl, excl);
    }

    public static int solveTab(int[] satisfaction){
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int[][] dp = new int[n+1][n+1];

        for(int index = n - 1; index >= 0; index--){
            for(int time = index; time >= 0; time--){
                int incl = satisfaction[index] * (time + 1) + dp[index + 1][time + 1];
                int excl = dp[index + 1][time];
                dp[index][time] = Math.max(incl, excl);
            }
        }

        

        return dp[0][0];
    }

    public int maxSatisfaction(int[] satisfaction) {
        int sum=0,res=0;
        int n=satisfaction.length;
        Arrays.sort(satisfaction);
        for(int i=n-1;i>=0;i--){
            sum+=satisfaction[i];
            if(sum<0) break;
            res+=sum;
        }
        return res;
    }
}
