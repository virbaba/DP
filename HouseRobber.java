import java.util.Arrays;

public class Solution {

	public static int maxMoneyRec(int[] houses, int index, int n){
		if(index >= n)
			return 0;
		int incl = houses[index] + maxMoneyRec(houses, index+2, n);
		int excl = maxMoneyRec(houses, index+1, n);
		return Math.max(incl, excl);
	}

	public static int maxMoneyDp(int[] houses, int index, int n, int[] dp){
		if(index >= n)
			return 0;
		
		if(dp[index] != -1)
			return dp[index];
		
		int incl = houses[index] + maxMoneyRec(houses, index+2, n);
		int excl = maxMoneyRec(houses, index+1, n);
		dp[index] = Math.max(incl, excl);
		return dp[index];
	}

	public static int maxMoneyTab(int[] houses, int index, int n){
		if(n == 0)
			return 0;
		if(n == 1)
			return houses[0];

		int[] dp = new int[n];
		dp[0] = houses[0];
		dp[1] = Math.max(houses[0], houses[1]);

		for(int i = 2; i < n; i++){
			int incl = houses[i] + dp[i - 2];
			int excl = dp[i - 1];
			dp[i] = Math.max(incl, excl);
		}

		return dp[n-1];
	}

	public static int spaceOpti(int[] houses, int n){
		if(n == 0)
			return 0;
		
		int prevFirst = 0;
		int prevSecond = 0;

		for(int i = 0; i < n; i++){
			int incl = houses[i] + prevSecond;
			int excl = prevFirst;
			int ans = Math.max(incl, excl);
			prevSecond = prevFirst;
			prevFirst = ans;
		}

		return prevFirst;
	}

	public static int maxMoneyLooted(int[] houses) {
		//Your code goes here
		int n = houses.length;
		// return maxMoneyRec(houses, 0, n);

		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);

		return spaceOpti(houses, n);

	}
}
