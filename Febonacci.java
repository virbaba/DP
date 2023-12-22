import java.util.*;
public class Solution {

	public static int febo(int n, int[] dp){
		if(n == 0 || n == 1)
			return n;
		
		if(dp[n] != -1)
			return dp[n];
		dp[n] = febo(n-1, dp) + febo(n-2, dp);
		return dp[n];
	}

	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
		*/
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);

		int ans = febo(n, dp);

		System.out.print(ans);
		
	}

}
