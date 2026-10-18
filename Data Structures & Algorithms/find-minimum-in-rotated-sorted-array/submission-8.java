class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = (left + right) / 2;

            int b = nums[mid];
            int c = nums[right];

            if (b > c) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
