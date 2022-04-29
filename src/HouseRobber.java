public class HouseRobber {

    /**
     * 198. House Robber
     *
     * dp[i] means the max amount of money should be made at ith position
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        for(int i=2; i<n; i++){
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[n-1];
    }


    public int rob2(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];

        // only need dp[i-1] and dp[i-2]
        // include = dp[i-1], exclude = dp[i-2]
        int include = 0, exclude = 0;
        for(int pos = 0; pos<n; pos++){
            int i = include, e = exclude;
            // include means rob nums[i]
            include = e + nums[pos];
            // exclude means not rob
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }


    /**
     * 213. House Robber II
     *
     * two scenarios: rob in [0, nums.length-2] OR rob in [1, nums.length-1]
     * @param nums
     * @return
     */
    public int robInCycle(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        return Math.max(helper(nums, 0, n-2), helper(nums, 1, n-1));
    }


    private int helper(int[] nums, int start, int end){
        int include = 0, exclude = 0;
        for(int pos = start; pos<=end; pos++){
            int i = include, e = exclude;
            // include means rob nums[i]
            include = e + nums[pos];
            // exclude means not rob
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
}
