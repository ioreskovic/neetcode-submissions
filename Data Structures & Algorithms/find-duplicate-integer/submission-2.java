class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (true) {
            slow = next(slow, nums);
            fast = next(next(fast, nums), nums);
            if (slow == fast) break;
        }

        int slow2 = 0;
        while (slow != slow2) {
            slow = next(slow, nums);
            slow2 = next(slow2, nums);
        }

        return slow;
    }

    private int next(int i, int[] nums) {
        return nums[i];
    }
}
