class NumArray {
    private final int[] sums;

    public NumArray(int[] nums) {
        this.sums = new int[nums.length + 1];
        this.sums[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            this.sums[i+1] = this.sums[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */