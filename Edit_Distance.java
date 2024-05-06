import java.util.Arrays;

public class Solution {

    public static int solveRec(String str1, String str2, int i, int j) {
        if (i < 0)
            return j + 1;

        if (j < 0)
            return i + 1;

        if (str1.charAt(i) == str2.charAt(j))
            return solveRec(str1, str2, i - 1, j - 1);

        int insertAns = 1 + solveRec(str1, str2, i, j - 1);
        int deleteAns = 1 + solveRec(str1, str2, i - 1, j - 1);
        int replaceAns = 1 + solveRec(str1, str2, i - 1, j);

        return Math.min(insertAns, Math.min(replaceAns, deleteAns));
    }

    public static int solve(String str1, String str2) {
        solveRec(str1, str2, str1.length() - 1, str2.length() - 1);
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n][m];

        for (int[] arr : dp)
            Arrays.fill(arr, -1);

        return 0;
    }

  public static int solveMemo(String str1, String str2, int i, int j, int[][] dp) {
        if (i < 0)
            return j + 1;

        if (j < 0)
            return i + 1;

        if (str1.charAt(i) == str2.charAt(j))
            return solveMemo(str1, str2, i - 1, j - 1, dp);

        int insertAns = 1 + solveMemo(str1, str2, i, j - 1, dp);
        int deleteAns = 1 + solveMemo(str1, str2, i - 1, j - 1, dp);
        int replaceAns = 1 + solveMemo(str1, str2, i - 1, j, dp);

        return Math.min(insertAns, Math.min(replaceAns, deleteAns));
    }

    

    public static int editDistance(String str1, String str2) {
        // Your code goes here
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = i;

        for (int j = 0; j <= m; j++)
            dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i-1][j-1];
                else {
                    int insertAns = 1 + dp[i][j-1];
                    int deleteAns = 1 + dp[i-1][j-1];
                    int replaceAns = 1 + dp[i-1][j];

                    dp[i][j] = Math.min(insertAns, Math.min(replaceAns, deleteAns));
                }
            }
        }

        return dp[n][m];
    }
}
