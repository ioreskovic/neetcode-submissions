class Solution {
    //       l
    // 1 2 3 4 4
    //           r

    // compare l vs r
    // if different, advance both
    // if same, advance both, copy, k++
    public int removeDuplicates(int[] nums) {
        int l = 0;
        int r = 0;

        while (r < nums.length) {
            while (r < nums.length && nums[r] == nums[l]) {
                r++;
            }
            if (r >= nums.length) return l + 1;
            if (l >= nums.length - 1) return l + 1;

            nums[++l] = nums[r];
            // find first r different from l
            // set nums[++l]=nums[r]
        }

        return l + 1;
    }
}