class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            if (left == right) return nums[left];
            int mid = (left + right) / 2;

            int a = nums[left];
            int b = nums[mid];
            int c = nums[right];

            if (b > c) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }
}
