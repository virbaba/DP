class Solution {
    public int solveRec(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length())
            return 0;

        if (text1.charAt(i) == text2.charAt(j))
            return 1 + solveRec(text1, text2, i + 1, j + 1);

        // if moving i
        int iForward = solveRec(text1, text2, i + 1, j);
        // if moving j
        int jForward = solveRec(text1, text2, i, j + 1);

        return Math.max(iForward, jForward);
    }

    public int solveMemo(String text1, String text2, int i, int j, int[][] dp) {
        if (i == text1.length() || j == text2.length())
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (text1.charAt(i) == text2.charAt(j))
            return 1 + solveMemo(text1, text2, i + 1, j + 1, dp);

        // if moving i
        int iForward = solveMemo(text1, text2, i + 1, j, dp);
        // if moving j
        int jForward = solveMemo(text1, text2, i, j + 1, dp);

        return dp[i][j] = Math.max(iForward, jForward);
    }

    public int solveTab(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = 1 + dp[i + 1][j + 1];

                else {
                    // if moving i
                    int iForward = dp[i+1][j];
                    // if moving j
                    int jForward = dp[i][j+1];

                    dp[i][j] = Math.max(iForward, jForward);
                }
            }
        }

        return dp[0][0];

    }

    public void solve(String text1, String text2) {
        solveRec(text1, text2, 0, 0);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return solveTab(text1, text2);
    }
}
