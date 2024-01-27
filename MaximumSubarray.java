import java.util.* ;
import java.io.*; 

import java.util.ArrayList;

public class Solution {
	public static ArrayList<Integer> maximumsumSubarray(int n, int arr[]) {
		// Write your code here
		ArrayList<Integer> result = new ArrayList<>();

        if (n == 0) {
            return result; // Empty array, return an empty list
        }

        int currentSum = arr[0];
        int maxSum = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum += arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        // Adding the subarray to the result list
        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }

        return result;
	}
}
