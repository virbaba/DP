import java.util.* ;
import java.io.*; 
public class Solution {

	public static int solveRec(ArrayList<Integer> nums, int index, int n){
		if(index >= n)
			return 0;

		
		// including current element
		int sum1 = nums.get(index) + solveRec(nums, index+2, n);
		// excluding current element
		int sum2 = solveRec(nums, index + 1, n);

		int maxSum = Math.max(sum1, sum2);
		return maxSum;
	}
	public static int solveMemo(ArrayList<Integer> nums, int index, int n, int[] dp){
		if(index >= n)
			return 0;

		
		// including current element
		int sum1 = nums.get(index) + solveMemo(nums, index+2, n, dp);
		// excluding current element
		int sum2 = solveMemo(nums, index + 1, n, dp);

		int maxSum = Math.max(sum1, sum2);
		return dp[index];
	}
	public static int solveTab(ArrayList<Integer> nums, int n){
        if (n == 0) return 0;
        
        if (n == 1) return nums.get(0);

        int[] dp = new int[n];
        dp[0] = nums.get(0);
        dp[1] = Math.max(nums.get(0), nums.get(1));

        for (int i = 2; i < n; i++) {
			int incl = nums.get(i) + dp[i - 2];
			int excl = dp[i - 1];
            dp[i] = Math.max(incl, excl);
        }

        return dp[n - 1];

	}
	public static int spaceOptimization(ArrayList<Integer> nums){
		int n = nums.size();

		// Two variables to store the previous results.
		int prevFirst = 0;
		int prevSecond = 0;

		for (int i = 0; i < n; i++) {
			int ans = Math.max(prevSecond + nums.get(i), prevFirst);
			prevSecond = prevFirst;
			prevFirst = ans;
		}
		return prevFirst;
	}
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		int n = nums.size();
		return spaceOptimization(nums);

		
	}
}
