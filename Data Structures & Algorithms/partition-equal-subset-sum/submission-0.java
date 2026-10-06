class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }

        if (sum % 2 == 1) return false;
        int target = sum / 2;

        int[][] dp = new int[len + 1][target + 1];

        for (int r = 0; r <= len; r++) {
            dp[r][0] = 0;
        }

        for (int c = 0; c <= target; c++) {
            dp[0][c] = 0;
        }

        for (int r = 1; r <= len; r++) {
            for (int c = 1; c <= target; c++) {
                int num = nums[r - 1];
                int prevSum = c - num;

                int included = 0;
                if (prevSum >= 0) {
                    included = num + dp[r - 1][prevSum];
                }
                if (included > target) {
                    included = 0;
                }

                int excluded = dp[r - 1][c];
                dp[r][c] = Math.max(included, excluded);
            }
        }

        return dp[len][target] == target;
    }
}
