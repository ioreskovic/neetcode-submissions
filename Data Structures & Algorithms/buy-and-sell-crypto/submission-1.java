class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;

        int maxFuturePrice = 0;
        int maxProfit = 0;

        for (int i = len - 2; i >= 0; i--) {
            maxFuturePrice = Math.max(maxFuturePrice, prices[i + 1]);
            var profit = maxFuturePrice - prices[i];
            maxProfit = Math.max(maxProfit,profit);
        }

        return maxProfit;
    }
}
