import java.util.*;

public class Solution {
	public static long countRec(int n) {
		if (n <= 1)
			return 0;
		if (n == 2)
			return 1;

		return ((n - 1) * (countDerangements(n - 2) + countDerangements(n - 1))) % 1000000007;
	}

	public static long countMemo(int n, long[] dp) {
		if (n <= 1)
			return 0;
		if (n == 2)
			return 1;

		if (dp[n] != -1)
			return dp[n];

		dp[n] = ((n - 1) * (countMemo(n - 2, dp) + countMemo(n - 1, dp))) % 1000000007;

		return dp[n];
	}

	public static long countTab(int n) {
		if (n <= 1)
			return 0;
		if (n == 2)
			return 1;

		long[] dp = new long[n + 1];
		dp[1] = 0;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = ((i - 1) * (dp[i - 2] + dp[i - 1])) % 1000000007;
		}

		return dp[n];
	}

	public static long countSpc(int n) {
		if (n == 1 || n == 2) {
			return n - 1;
		}

		// Variable for storing prev values
		long second = 0;
		long first = 1;

		// manner using above recursive formula
		for (int i = 3; i <= n; ++i) {
			long cur = ((i - 1) * (second + first)) % 1000000007;
			second = first;
			first = cur;
		}

		// Return result for n
		return first;
	}

	public static long countDerangements(int n) {
		// Write your code here.
		// long[] dp = new long[n+1];
		// Arrays.fill(dp, -1);
		// return countMemo(n, dp);
		return countSpc(n);
	}
}
