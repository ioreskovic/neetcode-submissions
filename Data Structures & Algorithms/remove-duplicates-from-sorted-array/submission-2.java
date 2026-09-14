class Solution {
    //       l
    // 1 2 3 4 4
    //           r

    // compare l vs r
    // if different, advance both
    // if same, advance both, copy, k++
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int l = 1;
        int r = 1;

        while (l < nums.length && r < nums.length) {
            if (nums[r] != nums[l - 1]) {
                nums[l++] = nums[r];
            }
            r++;
        }

        return l;
    }
}