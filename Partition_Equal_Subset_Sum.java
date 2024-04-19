class Solution {
    public boolean solveRec(int[] nums, int index, int n, int target){
        if(index >= n || target < 0)
            return false;
        
        if(target == 0)
            return true;
        
        boolean incl = solveRec(nums, index + 1, n, target - nums[index]);
        boolean excl = solveRec(nums, index + 1, n, target);
    
        return incl || excl;
    }

    public boolean solveMemo(int[] nums, int index, int n, int target, boolean[][] dp){
        if(index >= n || target < 0)
            return false;
        
        if(target == 0)
            return true;
        
        if(dp[index][target])
            return dp[index][target];
        
        boolean incl = solveMemo(nums, index + 1, n, target - nums[index], dp);
        boolean excl = solveMemo(nums, index + 1, n, target, dp);
    
        return dp[index][target] = incl || excl;
    }

    public boolean solveTab(int[] nums){
        int target = 0;
    for(int e : nums){
        target += e;
    }

    int n = nums.length;
    if(target % 2 != 0)
        return false;

    target /= 2;

    boolean[] curr = new boolean[target + 1];
    boolean[] next = new boolean[target + 1];

    next[0] = true;

    for(int index = n - 1; index >= 0; index--){
        for(int t = 1; t <= target; t++){
            boolean incl = false;
            if(t - nums[index] >= 0)
                incl = next[t - nums[index]];
            boolean excl = next[t];
            curr[t] = incl || excl;
        }
        // Copy values from curr to next
        System.arraycopy(curr, 0, next, 0, target + 1);
    }

    return next[target];
        
    }



    public boolean canPartition(int[] nums) {
        return solveTab(nums);
    }
}
