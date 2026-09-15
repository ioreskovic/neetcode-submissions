class Solution {
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        int[] leftToRight = new int[len];
        int[] rightToLeft = new int[len];

        leftToRight[0] = nums[0];
        rightToLeft[len - 1] = nums[len - 1];

        for (int i = 1; i < len; i++) {
            leftToRight[i] = leftToRight[i - 1] + nums[i];
        }

        for (int j = len - 2; j >= 0; j--) {
            rightToLeft[j] = rightToLeft[j + 1] + nums[j];
        }

        for (int i = 0; i < len; i++) {
            if (leftToRight[i] == rightToLeft[i]) return i;
        }

        return -1;
    }
}