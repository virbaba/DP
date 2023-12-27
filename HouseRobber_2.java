import java.util.* ;
import java.io.*; 
public class Solution {
	private static int robHelper(int[] nums, int start, int end) {
        int prevFirst = 0;
        int prevSecond = 0;

        for (int i = start; i <= end; i++) {
            int incl = nums[i] + prevSecond;
            int excl = prevFirst;
            int current = Math.max(incl, excl);
            prevSecond = prevFirst;
            prevFirst = current;
        }

        return prevFirst;
    }

	public static long houseRobber(int[] nums) {
		// Write your code here.
		if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        
        // Calculate the maximum amount robbed considering robbing the first house and excluding the last house
        int robFirst = robHelper(nums, 0, n - 2);
        
        // Calculate the maximum amount robbed considering excluding the first house and robbing the last house
        int robLast = robHelper(nums, 1, n - 1);

        // Return the maximum between the two cases
        return Math.max(robFirst, robLast);		
	}	
}
