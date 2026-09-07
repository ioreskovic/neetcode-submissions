class Solution {
    public int maxSubArray(int[] nums) {
        int maxFrom, maxTo = 0;
        int maxSum = nums[0];
        int from = 0;
        int currSum = 0;

        for (int to = 0; to < nums.length; to++) {
            if (currSum < 0) {
                from = to;
                currSum = 0;
            }

            currSum += nums[to];
            if (currSum > maxSum) {
                maxSum = currSum;
                maxFrom = from;
                maxTo = to;
            }
        }

        return maxSum;
    }
}
