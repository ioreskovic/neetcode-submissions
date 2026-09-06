class Solution {
    public int rob(int[] nums) {
        // at current house, i can take it or I can skip it. so 2^n possibilities
        // start as if you only have 1 house to rob, max is just rob it
        // next, if I rob this, then i take this plus n-2nd result, OR i skip this and take n-1st result

        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        

        int[] soFar = new int[nums.length];
        soFar[0] = nums[0];
        soFar[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            soFar[i] = Math.max(soFar[i - 1], soFar[i - 2] + nums[i]);
        }

        return soFar[nums.length - 1];
    }
}
