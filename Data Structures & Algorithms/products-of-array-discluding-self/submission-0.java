class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        int[] leftToRight = new int[len + 1];
        int[] rightToLeft = new int[len + 1];

        leftToRight[0] = 1;
        rightToLeft[len] = 1;

        for (int i = 1; i < len; i++) {
            leftToRight[i] = leftToRight[i - 1] * nums[i - 1];
        }
        for (int i = len - 1; i >= 0; i--) {
            rightToLeft[i] = rightToLeft[i + 1] * nums[i];
        }

        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = leftToRight[i] * rightToLeft[i + 1];
        }

        return result;
    }
}  
