package Grind75.array;

import java.util.HashMap;
import java.util.Map;

public class PartitionEqualSubsetSum_416 {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for(int num: nums){
            for(int i = target; i>=0; i--){
                if(num > i) continue;
                dp[i] = dp[i] || dp[i-num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] input = {3,3,3,4,5};
        canPartition(input);
    }

}
