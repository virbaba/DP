import java.util.*;
import java.io.*;

public class Solution {
    public static int solveRec(int[] num, int tar, int n) {
        if (tar == 0)
            return 1;
        if (tar < 0)
            return 0;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += solveRec(num, tar - num[i], n);
        }
        return ans;
    }

    public static int solveMemo(int[] num, int tar, int n, int[] dp) {
        if (tar == 0)
            return 1;
        if (tar < 0)
            return 0;

        if (dp[tar] != -1)
            return dp[tar];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += solveMemo(num, tar - num[i], n, dp);
        }
        dp[tar] = ans;
        return dp[tar];
    }

    public static int solveTab(int[] num, int tar, int n) {
        if (tar == 0)
            return 1;
        if (tar < 0)
            return 0;

        int[] dp = new int[tar + 1];
        Arrays.fill(dp, 0);

        dp[0] = 1;

        for (int t = 1; t <= tar; t++) {
            for (int i = 0; i < n; i++) {
                if ((t - num[i]) >= 0)
                    dp[t] += dp[t - num[i]];
            }
        }
        return dp[tar];
    }

    public static int findWays(int num[], int tar) {
        // Write your code here..
        int n = num.length;

        return solveTab(num, tar, n);
    }

}
