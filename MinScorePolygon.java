import java.util.*;
import java.io.*;

public class Solution {
	public static int solveRec(ArrayList<Integer> arr, int i, int j) {
		if (i + 1 == j)
			return 0;

		int ans = Integer.MAX_VALUE;
		for (int k = i + 1; k < j; k++) {
			int res = (arr.get(i) * arr.get(k) * arr.get(j)) + solveRec(arr, i, k) + solveRec(arr, k, j);
			ans = Math.min(ans, res);
		}

		return ans;
	}

	public static int solveMemo(ArrayList<Integer> arr, int i, int j, int[][] dp) {
		if (i + 1 == j)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		int ans = Integer.MAX_VALUE;
		for (int k = i + 1; k < j; k++) {
			int res = (arr.get(i) * arr.get(k) * arr.get(j)) + solveMemo(arr, i, k, dp) + solveMemo(arr, k, j, dp);
			ans = Math.min(ans, res);
		}
		dp[i][j] = ans;
		return ans;
	}

	public static int solveTab(ArrayList<Integer> arr, int n) {

		int[][] dp = new int[n][n];

		for (int[] ar : dp) {
			Arrays.fill(ar, 0);
		}

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 2; j < n; j++) {
				int ans = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					int res = (arr.get(i) * arr.get(k) * arr.get(j)) + dp[i][k]
							+ dp[k][j];
					ans = Math.min(ans, res);
				}
				dp[i][j] = ans;
			}
		}
		return dp[0][n-1];
	}

	public static int minimumTriangleScore(ArrayList<Integer> arr, int n) {
		// Write your code here.
		return solveTab(arr, n);
	}
}
