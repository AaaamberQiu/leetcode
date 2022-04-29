public class BuyAndSellStock {

    /**
     * 121. Best Time to Buy and Sell Stock
     * 1) one transaction
     * 2) cannot buy and sell at the same time
     * <p>
     * => max sum of subarray, where subarray[i] is the profit made in [i-1, i]
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int currProfit = 0;
        int ret = 0;
        for (int i = 1; i < n; i++) {
            int benefit = prices[i] - prices[i - 1];
            currProfit = Math.max(currProfit + benefit, benefit);
            ret = Math.max(ret, currProfit);
        }
        return ret > 0 ? ret : 0;
    }

    /**
     * 122. Best Time to Buy and Sell Stock II
     * 1) multiple transactions
     * 2) can buy and sell at the same day
     * 3) can only hold one share of the stock
     * <p>
     * => greedy algorithm
     *
     * @param prices
     * @return
     */
    public int maxProfitWithMultiTrans(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int ret = 0;
        for (int i = 1; i < n; i++) {
            int benefit = prices[i] - prices[i - 1];
            ret += benefit > 0 ? benefit : 0;
        }
        return ret;
    }


    /**
     * 309. Best Time to Buy and Sell Stock with Cooldown
     *
     * @param prices
     * @return
     */
    public int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[] buy = new int[n];
        int[] sell = new int[n];

        buy[0] = -prices[0];
        buy[1] = -Math.min(prices[0], prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, buy[0] + prices[1]);
        for (int i = 2; i < n; i++) {
            // two options: not buy or buy
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            // two options: not sell or sell
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }

    /**
     * 714. Best Time to Buy and Sell Stock with Transaction Fee
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfitWithTransFee(int[] prices, int fee) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[n - 1];
    }


    /**
     * 123. Best Time to Buy and Sell Stock III
     *
     * dp[k][i]: the max profit for k transactions on ith day
     * dp[k][i] = max(dp[k][i-1], dp[k-1][j-1] + prices[i] - princes[j])
     *
     * since it has at most 2 transactions, buy1 sell1 buy2 sell2 are used to record the max profit made when buy/sell at ith day
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitInTwoTrans(int k, int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;

        for(int i = 0; i<prices.length; i++){
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);

            // profit made by the first transaction is the base of the second trans
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }


    /**
     * 188. Best Time to Buy and Sell Stock IV
     *
     * dp[k][i]: the max profit for k transactions on ith day
     * two options:
     * 1) not sell on ith day, then the profit should be same as dp[k][i-1]
     * 2) sell on ith day, then the profit should be dp[k-1][j-1] + prices[i] - prices[j], j in [0,i) is day to buy
     *
     * @param prices
     * @return
     */
    public int maxProfitInKTrans(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[][] dp = new int[3][n];
        // when k = 0, profit = 0
        for (int count = 1; count <= 2; count++) {
            for(int i = 1; i<n; i++){
                // buy on day 0, sell on day i
                int maxProfitSellOnI = Math.max(0, prices[i] - prices[0]);
                for(int j = 1; j<i; j++){
                    // buy on day j, sell on day i
                    maxProfitSellOnI = Math.max(maxProfitSellOnI, dp[count-1][j-1] + prices[i] - prices[j]);
                }
                dp[k][i] = Math.max(dp[count][i-1], maxProfitSellOnI);
            }
        }
        return dp[k][n - 1];
    }


    public int maxProfitInKTrans2(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[][] dp = new int[k+1][n];
        // when k = 0, profit = 0
        for (int count = 1; count <= k; count++) {
            int maxTemp = -prices[0]; // keep track of max profit buying between [0,i]
            for(int i = 1; i<n; i++){
                maxTemp = Math.max(maxTemp, dp[count-1][i-1] - prices[i]);
                dp[count][i] = Math.max(dp[count][i-1], prices[i] + maxTemp);
            }
        }
        return dp[k][n - 1];
    }

}
