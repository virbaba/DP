class Solution {
    
    
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;
        if(n == 1)
            return 0;
        
        int min = prices[0];
        for(int i = 1; i < n; i++){
            if(min < prices[i]){
                int diff = prices[i] - min;
                maxProfit = Math.max(maxProfit, diff);
            }
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }
}
