import java.util.* ;
import java.io.*; 
public class Solution {
    public static int solveRec(int heights[], int i){
        if(i == 0)
            return 0;
        
        int left = solveRec(heights, i - 1) + Math.abs(heights[i] - heights[i-1]);
        int right = Integer.MAX_VALUE;
        if(i > 1){
            right = solveRec(heights, i - 2) + Math.abs(heights[i] - heights[i-2]);
        }
        return Math.min(left, right);
    }

    public static int solveMemo(int heights[], int i, int[] dp){
        if(i == 0)
            return 0;

        if(dp[i] != -1)
            return dp[i];
        
        int left = solveMemo(heights, i - 1, dp) + Math.abs(heights[i] - heights[i-1]);
        int right = Integer.MAX_VALUE;
        if(i > 1){
            right = solveMemo(heights, i - 2, dp) + Math.abs(heights[i] - heights[i-2]);
        }
        dp[i] = Math.min(left, right);
        return dp[i];
    }

    public static int solveTab(int heights[], int n){
        if(n == 0)
            return 0;

        int[] dp = new int[n];
        dp[0] = 0;

        for(int i = 1; i < n; i++){
            int left = dp[i-1] + Math.abs(heights[i] - heights[i-1]);
            int right = Integer.MAX_VALUE;
            if(i > 1){
                right = dp[i - 2] + Math.abs(heights[i] - heights[i-2]);
            }
            dp[i] = Math.min(left, right);
        }
        
       
        return dp[n-1];
    }

    public static int solveSO(int heights[], int n){
        if(n == 0)
            return 0;

       int first = 0;
       int second = 0;

        for(int i = 1; i < n; i++){
            int left = first + Math.abs(heights[i] - heights[i-1]);
            int right = Integer.MAX_VALUE;
            if(i > 1){
                right = second + Math.abs(heights[i] - heights[i-2]);
            }
            int ans = Math.min(left, right);
            second = first;
            first = ans;
        }
        
       
        return first;
    }

    public static int frogJump(int n, int heights[]) {

        // Write your code here..
        return solveSO(heights, n);
    }

}
