class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            int midVal = nums[mid];

            if (target < midVal) {
                r = mid - 1;
            } else if (target > midVal) {
                l = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
