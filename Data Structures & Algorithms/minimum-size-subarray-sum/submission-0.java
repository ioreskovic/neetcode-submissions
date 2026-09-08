class Solution {
    //                   R
    // [2, 1, 5, 1, 5, 3], 10
    //           L
    //  9, 3
    // while < t, sum+=n[r]
    // esle start shrinking
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for ( int
            l = 0,
            r = 0;
            r < nums.length;
            r ++
        ) {
            sum += nums[r];
            while (sum >= target) {
                minLen = Math.min(minLen, (r - l + 1));
                sum -= nums[l++];
            }
        }

        if (minLen == Integer.MAX_VALUE) return 0;
        return minLen;
    }
}