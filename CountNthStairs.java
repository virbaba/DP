import java.util.*;
import java.io.*;

public class Solution {
    // public static int solve(long n, int[] dp){
    //     if(n == 0)  
    //         return 1;
    //     if(n <= 2)
    //         return (int)n;
    //     if(dp[(int)n] != -1)
    //         return dp[(int)n];
        
    //      dp[(int)n] = (solve(n-1, dp) + solve(n-2, dp)) % 1000000007;
    //      return dp[(int)n];
    // }
    public static int countDistinctWayToClimbStair(long nStairs) {
        // Write your code here.
        if (nStairs <= 0) {
            return 1; // No stairs, no ways to climb
        } else if (nStairs <= 2) {
            return (int) nStairs; // If there are 1 or 2 stairs, there are nStairs ways to climb
        }

        

        int mod = 1000000007; // Modulo to prevent integer overflow
        int[] dp = new int[(int) nStairs + 1];
        dp[1] = 1; // If there is 1 stair, there is only one way to climb
        dp[2] = 2; // If there are 2 stairs, there are two ways to climb: (1, 1) or (2)

        for (int i = 3; i <= nStairs; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }

        return dp[(int) nStairs];

        // int[] dp = new int[(int)nStairs + 1];
        // Arrays.fill(dp, -1);
        // return solve(nStairs, dp);
    }
}
