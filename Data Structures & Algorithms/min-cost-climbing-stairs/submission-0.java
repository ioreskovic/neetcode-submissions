class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        int[] min = new int[len + 1];

        min[0] = cost[0];
        min[1] = cost[1];

        for (int i = 2; i <= len; i++) {
            var currCost = (i == len) ? 0 : cost[i];
            min[i] = currCost + Math.min(min[i -1], min[i - 2]);
        }

        return min[len];
    }
}
