import java.util.*;
import java.io.*;

public class Solution {
    public static int solveRec(int[] weight, int[] value, int n, int maxWeight, int index) {
        if (index >= n)
            return 0;

        int incl = Integer.MIN_VALUE;
        if (weight[index] <= maxWeight)
            incl = value[index] + solveRec(weight, value, n, maxWeight - weight[index], index + 1);

        int excl = solveRec(weight, value, n, maxWeight, index + 1);

        return Math.max(incl, excl);
    }

    public static int solveMemo(int[] weight, int[] value, int n, int maxWeight, int index, int[][] dp) {
        if (index >= n)
            return 0;

        if (dp[index][maxWeight] != -1) {
            return dp[index][maxWeight];
        }

        int incl = Integer.MIN_VALUE;
        if (weight[index] <= maxWeight)
            incl = value[index] + solveMemo(weight, value, n, maxWeight - weight[index], index + 1, dp);

        int excl = solveMemo(weight, value, n, maxWeight, index + 1, dp);

        return dp[index][maxWeight] = Math.max(incl, excl);
    }

    public static int solveTab(int[] weight, int[] value, int n, int maxWeight) {

        int[][] dp = new int[n + 1][maxWeight + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int w = 0; w <= maxWeight; w++) {
                int incl = Integer.MIN_VALUE;
                if (weight[index] <= w)
                    incl = value[index] + dp[index + 1][w - weight[index]];

                int excl = dp[index + 1][w];

                dp[index][w] = Math.max(incl, excl);
            }
        }
        return dp[0][maxWeight];

    }

    public static int solveSO1(int[] weight, int[] value, int n, int maxWeight) {

        int[] curr = new int[maxWeight + 1];
        int[] next = new int[maxWeight + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int w = 0; w <= maxWeight; w++) {
                int incl = 0;
                if (weight[index] <= w)
                    incl = value[index] + next[w - weight[index]];

                int excl = next[w];

                curr[w] = Math.max(incl, excl);
            }
            // Copy values from next to curr
            System.arraycopy(curr, 0, next, 0, maxWeight + 1);
        }
        return next[maxWeight];

    }

    public static int solveSO2(int[] weight, int[] value, int n, int maxWeight) {

        int[] curr = new int[maxWeight + 1];
        

        for (int index = n - 1; index >= 0; index--) {
            for (int w = maxWeight; w >= 0; w--) {
                int incl = 0;
                if (weight[index] <= w)
                    incl = value[index] + curr[w - weight[index]];

                int excl = curr[w];

                curr[w] = Math.max(incl, excl);
            }
        }
        return curr[maxWeight];

    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        return solveSO2(weight, value, n, maxWeight);
    }
}
