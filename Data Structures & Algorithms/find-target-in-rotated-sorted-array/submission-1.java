class Solution {
    public int search(int[] nums, int t) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;

            int a = nums[left];
            int b = nums[mid];
            int c = nums[right];

            if (b == t) return mid;

            if (a <= b) { // left is sorted
                if (a <= t && t <= b) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (b <= t && t <= c) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
