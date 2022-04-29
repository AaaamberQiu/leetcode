package Grind75;

public class BestTimetoBuyandSellStock_121 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int max = 0, sum = 0;
        for(int i = 1; i<n; i++){
            int profit = prices[i] - prices[i-1];
            sum = Math.max(sum + profit, profit);
            max = Math.max(max, sum);
        }
        return max;
    }
}
