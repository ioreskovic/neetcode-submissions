class Solution {
    public int missingNumber(int[] nums) {
        long sum = 0;
        for (int n : nums) {
            sum += n;
        }

        long n = nums.length;

        return (int) ((n * (n + 1)) / 2 - sum);
    }
}
