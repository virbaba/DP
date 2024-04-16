import java.util.*;

public class Solution {

    public static int solveRec(int pre, int curr, int size, int[] a) {
        if (curr == size) {
            return 0;
        }

        int incl = 0;
        if (pre == -1 || a[curr] > a[pre])
            incl = 1 + solveRec(curr, curr + 1, size, a);

        int excl = solveRec(pre, curr + 1, size, a);

        return Math.max(incl, excl);

    }

    public static int solveMemo(int pre, int curr, int size, int[] a, int[][] dp) {
        if (curr == size) {
            return 0;
        }

        if (dp[curr][pre + 1] != -1)
            return dp[curr][pre + 1];

        int incl = 0;
        if (pre == -1 || a[curr] > a[pre])
            incl = 1 + solveMemo(curr, curr + 1, size, a, dp);

        int excl = solveMemo(pre, curr + 1, size, a, dp);

        return dp[curr][pre + 1] = Math.max(incl, excl);

    }

    public static int solveTab(int n, int[] a) {
        int[][] dp = new int[n + 1][n + 1];

        for (int curr = n - 1; curr >= 0; curr--) {
            for (int pre = curr - 1; pre >= -1; pre--) {
                int incl = 0;
                if (pre == -1 || a[curr] > a[pre])
                    incl = 1 + dp[curr + 1][curr + 1];

                int excl = dp[curr + 1][pre + 1];

                dp[curr][pre + 1] = Math.max(incl, excl);
            }
        }
        return dp[0][0];
    }

    public static int solveOptimal(int n, int[] arr) {
        int dp[] = new int[arr.length];
        int len = 0;
        for (int ele : arr) {
            int index = Arrays.binarySearch(dp, 0, len, ele);
            if (index < 0)
                index = -(index + 1);
            dp[index] = ele;
            if (index == len)
                len++;
        }
        return len;
    }

    public static int longestIncreasingSubsequence(int arr[]) {
        // Your code goes here
        int n = arr.length;
       return solveOptimal(n, arr);
    }

}
