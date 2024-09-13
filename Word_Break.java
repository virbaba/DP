import java.util.*;
import java.io.*;

public class Solution {
    public static boolean solveRec(String[] arr, int startIndex, String target) {
        if (startIndex == target.length())
            return true;

        for (String word : arr) {
            int end = startIndex + word.length();
            if (end <= target.length() && target.substring(startIndex, end).equals(word)) {
                boolean res = solveRec(arr, end, target);
                if (res)
                    return true;
            }
        }

        return false;
    }

    public static boolean solveMemo(String[] arr, int startIndex, String target, boolean[] dp) {
        if (startIndex == target.length())
            return true;

        if (dp[startIndex])
            return dp[startIndex];

        for (String word : arr) {
            int end = startIndex + word.length();
            if (end <= target.length() && target.substring(startIndex, end).equals(word)) {
                boolean res = solveMemo(arr, end, target, dp);
                if (res)
                    return dp[startIndex] = res;
            }
        }

        return dp[startIndex] = false;
    }

    public static boolean solveTab(String[] arr, int n, String target) {
        // Create a DP table to store results for substrings
        boolean[] dp = new boolean[target.length() + 1];

        // Initialize dp[target.length()] to true since an empty string can be formed from the end
        dp[target.length()] = true;

        // Fill the DP table from end to start
        for (int startIndex = target.length() - 1; startIndex >= 0; startIndex--) {
            // Check if the substring target[i...] can be formed by any word in arr
            for (String word : arr) {
                int end = startIndex + word.length();
                
                // Ensure we are within bounds before accessing dp[end]
                if (end <= target.length() && target.substring(startIndex, end).equals(word)) {
                    if(dp[end])
                        dp[startIndex] = dp[end];
                }
            }
            
        }

        // The result for the entire target is stored in dp[0]
        return dp[0];

    }

    public static Boolean wordBreak(String[] arr, int n, String target) {
        // Write your code here.

        return solveTab(arr, n, target);
    }
}
