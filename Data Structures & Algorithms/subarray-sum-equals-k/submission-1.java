class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;

        int[] prefixSum = new int[len + 1];
        prefixSum[0] = 0;
        HashMap<Integer, Integer> previousSumCount = new HashMap<>();
        previousSumCount.put(0, 1);
        int count = 0;

        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            count += previousSumCount.getOrDefault(prefixSum[i] - k, 0);
            previousSumCount.compute(prefixSum[i], (__, val) -> (val == null) ? 1 : val + 1);
        }


        return count;
    }
}