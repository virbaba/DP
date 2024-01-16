import java.util.*;

public class Solution {

	public static int solveRec(int n, int[] days, int[] cost, int curr) {
		if (curr == n)
			return 0;

		int oneDayPass = cost[0] + solveRec(n, days, cost, curr + 1);

		int next = curr;

		for (; next < n; next++) {
			if (days[next] >= days[curr] + 7)
				break;
		}

		int sevenDayPass = cost[1] + solveRec(n, days, cost, next);

		for (; next < n; next++) {
			if (days[next] >= days[curr] + 30)
				break;
		}
		int thirtyDayPass = cost[2] + solveRec(n, days, cost, next);
		return Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
	}

	public static int solveMemo(int n, int[] days, int[] cost, int curr, int[] dp) {
		if (curr == n)
			return 0;

		if (dp[curr] != -1)
			return dp[curr];

		int oneDayPass = cost[0] + solveMemo(n, days, cost, curr + 1, dp);

		int next = curr;

		for (; next < n; next++) {
			if (days[next] >= days[curr] + 7)
				break;
		}

		int sevenDayPass = cost[1] + solveMemo(n, days, cost, next, dp);

		for (; next < n; next++) {
			if (days[next] >= days[curr] + 30)
				break;
		}
		int thirtyDayPass = cost[2] + solveMemo(n, days, cost, next, dp);
		dp[curr] = Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
		return dp[curr];
	}

	public static int solveTab(int n, int[] days, int[] costs) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[n] = 0;

		for (int curr = n - 1; curr >= 0; curr--) {
			int oneDayPass = costs[0] + dp[curr + 1];

			int k = curr;
			for (; k < n; k++) {
				if (days[k] >= days[curr] + 7) {
					break;
				}
			}
			int sevenDayPass = costs[1] + dp[k];

			for (; k < n; k++) {
				if (days[k] >= days[curr] + 30) {
					break;
				}
			}
			int thirtyDayPass = costs[2] + dp[k];

			dp[curr] = Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
		}

		return dp[0];
	}

	public static int solveSpc(int n, int[] days, int[] costs) {
		Queue<int[]> sevenDaysPassQueue = new LinkedList<>();
		Queue<int[]> thirtyDaysPassQueue = new LinkedList<>();

		int totalCost = 0;

		for (int day : days) {
			while (!sevenDaysPassQueue.isEmpty() && sevenDaysPassQueue.peek()[0] + 7 <= day) {
				sevenDaysPassQueue.poll();
			}
			while (!thirtyDaysPassQueue.isEmpty() && thirtyDaysPassQueue.peek()[0] + 30 <= day) {
				thirtyDaysPassQueue.poll();
			}

			sevenDaysPassQueue.offer(new int[] { day, totalCost + costs[1] });
			thirtyDaysPassQueue.offer(new int[] { day, totalCost + costs[2] });

			totalCost = Math.min(totalCost + costs[0],
					Math.min(sevenDaysPassQueue.peek()[1], thirtyDaysPassQueue.peek()[1]));
		}

		return totalCost;
	}

	public static int minimumCoins(int n, int[] days, int[] cost) {

		// Write your code here
		return solveSpc(n, days, cost);
	}

}
