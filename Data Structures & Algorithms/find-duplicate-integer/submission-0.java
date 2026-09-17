class Solution {
    public int findDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (seen.contains(n)) return n;
            seen.add(n);
        }

        return -1;
    }
}
