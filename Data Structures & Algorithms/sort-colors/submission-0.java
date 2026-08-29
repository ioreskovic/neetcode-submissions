class Solution {
    public void sortColors(int[] nums) {
        int[] buckets = new int[3];
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i]]++;
        }
        
        int i = 0;
        for (int b = 0; b < buckets.length; b++) {
            for (int j = 0; j < buckets[b]; j++) {
                nums[i++] = b;
            }
        }
    }
}