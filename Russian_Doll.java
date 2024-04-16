class Solution {
    public int maxEnvelopes(int[][] arr) {
		if (arr == null || arr.length == 0
				|| arr[0] == null || arr[0].length != 2)
			return 0;

      // sorting the array according to width in increasing order
      // if width same the sort according to height in decreasing order
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				if (arr1[0] == arr2[0])
					return arr2[1] - arr1[1];
				else
					return arr1[0] - arr2[0];
			}
		});
        
		int dp[] = new int[arr.length];
		int len = 0;
		for (int[] envelope : arr) {
			int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
			if (index < 0)
				index = -(index + 1);
			dp[index] = envelope[1];
			if (index == len)
				len++;
		}
		return len;
    }
}
