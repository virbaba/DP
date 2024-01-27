/*
    Time Complexity: O(N)
    Space Complexity: O(1)

    max = arr[0]
    sum += arr[i]
    if(max > sum)
      max = sum;
    if(sum < 0)
      sum = 0
    where N is the length of the array.
*/

import java.util.ArrayList;

public class Solution {

    public static long maxSubarraySum(int arr[], int n) {
        long maxSum = 0, curSum = 0;

        for(int i = 0; i < n; i++) {
            curSum = Math.max(0L, curSum + arr[i]);
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }
}
