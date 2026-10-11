class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        int diff = n;
        for (int i = 0; i < n; i++) {
            diff ^= i ^ nums[i];
        }

        return diff;
    }
}
