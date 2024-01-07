import java.util.*;
import java.io.*;

public class Solution {
	static int MOD = 1000000007;
	public static int add(int a, int b){
		return ((a%MOD) + (b%MOD))%MOD;
	}

	public static int mul(int a, int b){
		return ((a%MOD) * (b%MOD))%MOD;
	}

	public static int solveRec(int n, int k){
		if(n == 0)
			return 0;
		if(n == 1)
			return k;
		if(n == 2)
			return add(k, mul(k, k-1));
		
		return add(mul(solveRec(n-2, k), k-1), mul(solveRec(n-1, k), k-1));
	}

	public static int solveMemo(int n, int k, int[] dp){
		if(n == 0)
			return 0;
		if(n == 1)
			return k;
		if(n == 2)
			return add(k, mul(k, k-1));

		if(dp[n] != -1)
			return dp[n];
		
		dp[n] =  add(mul(solveMemo(n-2, k, dp), k-1), mul(solveMemo(n-1, k, dp), k-1));
		
		return dp[n];
	}

	public static int solveTab(int n, int k){

		int[] dp = new int[n + 1];
		dp[0] = 0;
		
		if(n == 0)
			return dp[0];
		
		dp[1] = k;
		if(n == 1)
			return dp[1];
		
		dp[2] = add(k, mul(k, k-1));
		if(n == 2)
			return dp[2];
		
		for(int i = 3; i <= n; i++){
			dp[i] = add(mul(dp[i-2], k-1), mul(dp[i-1], k-1));
		}
		return dp[n];
	}

	public static int solveSpace(int n, int k){
		if(n == 0)
			return 0;
		if(n == 1)
			return k;
		
		long same = k * 1;
		long diff = k * (k - 1);
		long total  = (same + diff) % MOD;

		for(int i  = 3; i <= n; i++){
			long temp  = diff % MOD;
			same = temp * 1;
			diff = (total * (k-1)) % MOD;
			total = (same + diff) % MOD;
		}

		return (int)total;
	}

	

	public static int numberOfWays(int n, int k) {
		
	}
}
