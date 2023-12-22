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
