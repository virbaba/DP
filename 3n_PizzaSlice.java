class Solution {
    public static int solveRec(int[] slices, int index, int n) {
        if (n == 0 || index >= slices.length)
            return 0;

        // if i eat current slice
        int incl = slices[index] + solveRec(slices, index + 2, n - 1);
        // if i exclude current slice
        int excl = solveRec(slices, index + 1, n);

        return Math.max(incl, excl);

    }

    public static int solveMemo(int[] slices, int index, int n, int[][] dp, int size) {
        if (n == 0 || index >= size)
            return 0;

        if (dp[index][n] != -1)
            return dp[index][n];

        // if i eat current slice
        int incl = slices[index] + solveMemo(slices, index + 2, n - 1, dp, size);
        // if i exclude current slice
        int excl = solveMemo(slices, index + 1, n, dp, size);

        return dp[index][n] = Math.max(incl, excl);

    }

    public static int solveTab(int[] slices, int index, int size) {
        int totalSlice = slices.length;
        int k = totalSlice / 3;

        int[][] dp = new int[totalSlice + 1][k + 1];

        for (int i = size - 1; i >= index; i--) {
            for (int n = 1; n <= k; n++) {
                // if i eat current slice
                int incl = slices[i] + (i + 2 < totalSlice ? dp[i + 2][n - 1] : 0);
                // if i exclude current slice
                int excl = dp[i + 1][n];

                dp[i][n] = Math.max(incl, excl);
            }
        }
        return dp[index][k];
    }

    public int maxSizeSlices(int[] slices) {
        // if i eat first slice then i skip last slice
        int case1 = solveTab(slices, 0, slices.length - 1);

        // otherwise i skip first slice
        int case2 = solveTab(slices, 1, slices.length);

        return Math.max(case1, case2);
    }
}
