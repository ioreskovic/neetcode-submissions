class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;

        int[] maxFuturePrice = new int[len];
        maxFuturePrice[len - 1] = 0;

        for (int i = len - 2; i >= 0; i--) {
            maxFuturePrice[i] = Math.max(maxFuturePrice[i + 1], prices[i + 1]);
        }

        int maxProfit = 0;

        for (int i = 0; i < len; i++) {
            var butAtIthDayProfit = maxFuturePrice[i] - prices[i];
            maxProfit = Math.max(maxProfit, butAtIthDayProfit);
        }

        return maxProfit;
    }
}
