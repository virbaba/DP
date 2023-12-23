/*
    Solving min coins problem using recursion
*/
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int mCoinsRec(int amount, int[] coins){
        if(amount == 0)
            return 0;
        
        int minCoins = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            if(amount - coins[i] >= 0){
                int currCoins = mCoinsRec(amount - coins[i], coins);
                currCoins += 1;
                minCoins = Math.min(minCoins, currCoins);
            }
        }

        return minCoins;
    }
    public static int minimumCoins(int amount){
        // Write your code here.
        int[] coins = {1,2,5,10,20,50,100,500,1000};
        return mCoinsRec(amount, coins);
    }
}

/*
    Solving min coins problem using memoization
*/
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int mCoinsMemo(int amount, int[] coins, int[] dp){
        if(amount == 0)
            return 0;
        
        if(dp[amount] != -1)
            return dp[amount];

        int minCoins = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            if(amount - coins[i] >= 0){
                int currCoins = mCoinsMemo(amount - coins[i], coins, dp);
                currCoins += 1;
                minCoins = Math.min(minCoins, currCoins);
            }
        }

        dp[amount] = minCoins;
        return dp[amount];
    }
    public static int minimumCoins(int amount){
        // Write your code here.
        int[] coins = {1,2,5,10,20,50,100,500,1000};

        // memoization
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);

        return mCoinsMemo(amount, coins, dp);
    }
}


/*
    Tabulation Method
*/
import java.util.*;
import java.io.*;

public class Solution {
    public static int minimumCoins(int amounts) {
        // Write your code here.
        // in tabulation method dp[i] contain min coins for ith amount
        int[] dp = new int[amounts + 1];
        int[] coins = { 1, 2, 5, 10, 20, 50, 100, 500, 1000 };
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // suppose we want to find min coins for 100 then we should have
        // min coins from 1 to 99
        for (int amount = 1; amount <= amounts; amount++) {
            // run loop over coins
            for (int coin : coins) {
                // check for invalid index and dp[i-1]th value
                if ((amount - coin) >= 0 && dp[amount - coin] != Integer.MAX_VALUE)
                    dp[amount] = Math.min(dp[amount], dp[amount - coin] + 1);
            }
        }

        return (dp[amounts] != Integer.MAX_VALUE)?dp[amounts]:-1;
    }
}
/* fastest way */
import java.util.*;
import java.io.*;

public class Solution {
    public static int minimumCoins(int n) {
        // Write your code here.
        // in tabulation method dp[i] contain min coins for ith amount
        int[] denominations = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        int count = 0;
        int m = denominations.length;

        // Pick coins in decreasing order of their values.
        for (int i = m - 1; i >= 0; i--) {
            while (n >= denominations[i]) {
                n = n - denominations[i];
                count += 1;
            }
        }

        return count;
    }
}


