class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int range = 0;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            range += Math.abs(nums[i]);
        }

        int variants = 2 * range + 1;

        int[][] dp = new int[len + 1][variants];

        for (int c = 0; c < variants; c++) {
            dp[0][c] = 0;
        }
        dp[0][range] = 1; // we can achieve sum of 0 with 0-length array

        for (int r = 1; r <= len; r++) {
            int num = Math.abs(nums[r - 1]);

            for (int c = 0; c < variants; c++) {
                int addSum = 0;
                int addIdx = c - num;
                if (0 <= addIdx && addIdx < variants) { // in bounds
                    addSum = dp[r - 1][addIdx];
                } else if (num == c) {
                    addSum = 1;
                }

                int subSum = 0;
                int subIdx = c + num;
                if (0 <= subIdx && subIdx < variants) { // in bounds
                    subSum = dp[r - 1][subIdx];
                } else if (num == c) {
                    subSum = 1;
                }

                dp[r][c] = addSum + subSum;
            }
        }

        int resultCell = range + target;
        if (0 <= resultCell && resultCell < variants) return dp[len][range + target];
        return 0;
    }
}
