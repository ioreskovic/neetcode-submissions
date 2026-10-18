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

            if (a <= b && b <= c) return a;
            if (a > b) { left = left + 1; right = mid; }
            if (b > c) { left = mid + 1; }
        }

        return -1;
    }
}
