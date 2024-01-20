import java.util.*;

public class Solution {

	public static int solveRec(ArrayList<ArrayList<Integer>> mat, int n, int m, int i, int j, int[] max) {
		if (i > n || j > m)
			return 0;

		int right = solveRec(mat, n, m, i, j + 1, max);
		int diagonal = solveRec(mat, n, m, i + 1, j + 1, max);
		int down = solveRec(mat, n, m, i + 1, j, max);

		if (mat.get(i).get(j) == 1) {
			int ans = 1 + Math.min(right, Math.min(diagonal, down));
			max[0] = Math.max(max[0], ans);
			return ans;
		}
		return 0;
	}

	public static int solveMemo(ArrayList<ArrayList<Integer>> mat, int n, int m, int i, int j, int[] max, int[][] dp) {
		if (i > n || j > m)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		int right = solveMemo(mat, n, m, i, j + 1, max, dp);
		int diagonal = solveMemo(mat, n, m, i + 1, j + 1, max, dp);
		int down = solveMemo(mat, n, m, i + 1, j, max, dp);

		if (mat.get(i).get(j) == 1) {
			int ans = 1 + Math.min(right, Math.min(diagonal, down));
			max[0] = Math.max(max[0], ans);
			dp[i][j] = ans;
			return dp[i][j];
		}
		return dp[i][j] = 0;
	}

	public static int solveTab(ArrayList<ArrayList<Integer>> mat, int n, int m) {

		int[][] dp = new int[n + 1][m + 1];
		int max = 0; // Initialize max to zero

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				int right = dp[i][j + 1];
				int diagonal = dp[i + 1][j + 1];
				int down = dp[i + 1][j];

				if (mat.get(i).get(j) == 1) {
					dp[i][j] = 1 + Math.min(right, Math.min(diagonal, down));
					max = Math.max(max, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return max;
	}

	public static int solveSO1(ArrayList<ArrayList<Integer>> mat, int n, int m) {

		int[] curr = new int[m + 1];
		int[] next = new int[m + 1];
		int max = 0; // Initialize max to zero

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				int right = curr[j + 1];
				int diagonal = next[j + 1];
				int down = next[j];

				if (mat.get(i).get(j) == 1) {
					curr[j] = 1 + Math.min(right, Math.min(diagonal, down));
					max = Math.max(max, curr[j]);
				} else {
					curr[j] = 0;
				}
			}
			next = curr;
		}
		return max;
	}

	public static int solveSO2(ArrayList<ArrayList<Integer>> mat, int n, int m) {
		int max = 0; // Initialize max to zero

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (mat.get(i).get(j) == 1) {
					int right = (j + 1 < m) ? mat.get(i).get(j + 1) : 0;
					int diagonal = (i + 1 < n && j + 1 < m) ? mat.get(i + 1).get(j + 1) : 0;
					int down = (i + 1 < n) ? mat.get(i + 1).get(j) : 0;

					mat.get(i).set(j, 1 + Math.min(right, Math.min(diagonal, down)));
					max = Math.max(max, mat.get(i).get(j));
				} else {
					mat.get(i).set(j, 0);
				}
			}
		}
		return max;
	}

	public static int maximumAreaSquare(ArrayList<ArrayList<Integer>> mat, int n, int m) {
		// Write your code here
		int[] max = { 0 };
		int[][] dp = new int[n][m];

		int ans = solveSO2(mat, n, m);
		return ans * ans;

	}
}
