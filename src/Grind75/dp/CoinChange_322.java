package Grind75.dp;

public class CoinChange_322 {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];

        for(int i = 1; i<=amount; i++){
            int min = amount+1;
            for(int coin: coins){
                if(coin > i) continue;
                if(dp[i - coin] >= 0) min = Math.min(min, dp[i - coin] + 1);
            }
            if(min == amount + 1) dp[i] = -1;
            else dp[i] = min;
        }
        return dp[amount];
    }
}
