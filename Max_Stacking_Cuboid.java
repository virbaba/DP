class Solution {

    public static int maxHeight(int[][] cuboids) {
        for (int[] arr : cuboids) {
            Arrays.sort(arr);
        }

        Arrays.sort(cuboids,new Comparator<int[]>(){
            public int compare(int[]a,int[]b){
                if(a[0]!=b[0])return a[0]-b[0];
                else
                if(a[1]!=b[1])return a[1]-b[1];
                return a[2]-b[2];
            }
        });

        int n = cuboids.length;
        int[] dp = new int[n];
        dp[0] = cuboids[0][2];

        for (int i = 1; i < n; i++) {
            dp[i] = cuboids[i][2]; // initialize dp[i] with the height of the current cuboid
            for (int j = i-1; j >= 0; j--) {
                if (cuboids[i][0] >= cuboids[j][0] && cuboids[i][1] >= cuboids[j][1] && cuboids[i][2] >= cuboids[j][2]) {
                    // current cuboid can be stacked on top of previous cuboid
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
        }

        int maxHeight = 0;
        for (int height : dp) {
            maxHeight = Math.max(maxHeight, height);
        }
        
        return maxHeight;
    }
}
