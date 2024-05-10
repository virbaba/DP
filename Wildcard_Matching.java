class Solution {
    public boolean solveRec(String s, String p, int i, int j) {
        if (i == s.length() && j == p.length())
            return true;

        if (j >= p.length())
            return false;

        if (i >= s.length()) {
            // if remain pattern only contain * than return true because we can replace * by
            // empty string otherwise false
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*')
                    return false;
            }
            return true;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
            return solveRec(s, p, i + 1, j + 1);

        else if (p.charAt(j) == '*') {
            // if star is empty string || if star is replace by any character
            return solveRec(s, p, i, j + 1) || solveRec(s, p, i + 1, j);
        }

        return false;
    }

    public boolean solveMemo(String s, String p, int i, int j, boolean[][] dp) {
        if (i == s.length() && j == p.length())
            return true;

        if (j >= p.length())
            return false;

        if (i >= s.length()) {
            // if remain pattern only contain * than return true because we can replace * by
            // empty string otherwise false
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*')
                    return false;
            }
            return true;
        }

        if (dp[i][j])
            return dp[i][j];

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            dp[i][j] = solveMemo(s, p, i + 1, j + 1, dp);
            return dp[i][j];
        }

        else if (p.charAt(j) == '*') {
            // if star is empty string || if star is replace by any character
            dp[i][j] = solveMemo(s, p, i, j + 1, dp) || solveMemo(s, p, i + 1, j, dp);
            return dp[i][j];
        }

        return false;
    }

    public boolean solveTab(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[n][m] = true;

        for(int j = m - 1; j >= 0; j--){
            boolean flag = true;
            // if remain pattern only contain * than return true because we can replace * by
            // empty string otherwise false
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*'){
                    flag = false;
                    break;
                }
            }
            dp[n][j] = flag;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i][j] = dp[i+1][j+1];
                }

                else if (p.charAt(j) == '*') {
                    // if star is empty string || if star is replace by any character
                    dp[i][j] = dp[i][j+1] || dp[i+1][j];
                }
            }
        }

       return dp[0][0];
    }

    public boolean solve(String s, String p) {
        return solveRec(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p) {
        return solveTab(s, p);
    }
}
