class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int runLength = 0;
        int maxRunLength = 0;

        for (int i = 0; i < nums.length; i++) {
            var newRunLength = runLength + nums[i];

            if (newRunLength > runLength) {
                runLength = newRunLength;
            } else {
                runLength = 0;
            }

            if (runLength > maxRunLength) {
                maxRunLength = runLength;
            }
        }

        return maxRunLength;
    }

    // [] -> 0
    // [0] -> 0
    // [1] -> 1
    // [0, 0] -> 0
    // [0, 1] -> 1
    // [1, 0] -> 1
    // [1, 1] -> 2
}