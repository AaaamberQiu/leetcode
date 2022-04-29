import java.lang.invoke.LambdaMetafactory;
import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    /**
     * 322. Coin Change
     *
     * DP approach
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0 && amount != 0) return -1;

        int[] dp = new int[amount+1];
        int maxCount = amount + 1;
        for(int i = 1; i<=amount; i++){
            dp[i] = maxCount;
            for(int j = 0; j<coins.length; j++){
                if(i >= coins[j]){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }
        return dp[amount] == maxCount ? -1:dp[amount];
    }


    // recursion with memory
    public int coinChange2(int[] coins, int amount) {
        Map<Integer, Integer> memory = new HashMap<>();
        return helper(coins, amount, memory);
    }


    private static int helper(int[] coins, int amount, Map<Integer, Integer> memory){
        if(amount < 0) return -1;
        if(amount == 0) return 0;
        if(memory.containsKey(amount)) return memory.get(amount);

        int minCount = Integer.MAX_VALUE;
        for(int coin: coins){
            int count = helper(coins, amount-coin, memory);
            if(count>=0){
                minCount = Math.min(minCount, count+1);
            }
        }
        minCount = minCount == Integer.MAX_VALUE ? -1:minCount;
        memory.put(amount, minCount);
        return memory.get(amount);
    }


    /**
     * 518. Coin Change 2
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if(coins.length == 0 && amount != 0) return 0;

        // using coins.length+1: allow choosing no coins
        int[][] dp = new int[coins.length+1][amount+1];
        // using no coin to get 0 is one combination
        dp[0][0] = 1;
        for(int i = 1; i<=coins.length; i++){
            // dp[i][0] means how many way to use first ith coin to make up of the amount 0
            // => 1 way that we use none of the,
            dp[i][0] = 1;
            for(int j = 1; j<=amount; j++){
                dp[i][j] = dp[i-1][j] + (j-coins[i-1] >= 0 ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }


    public int change2(int amount, int[] coins) {
        if(coins.length == 0 && amount != 0) return 0;

        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin: coins){
            // dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
            // curr dp[j] = prev dp[j] + dp[j-coin]
            for(int j = 1; j<= amount; j++){
                dp[j] = dp[j] + (j-coin >= 0 ? dp[j-coin] : 0);
            }
        }
        return dp[amount];
    }
}
