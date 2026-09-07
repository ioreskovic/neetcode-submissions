class Solution {
    public int maxSubarraySumCircular(int[] nums) {     
        int n = nums.length;   
        int nums2[] = new int[n * 2];
        for (int i = 0; i < n; i++) {
            nums2[i] = nums[i];
            nums2[i + n] = nums[i];
        }

        int maxSum = nums2[0];

        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, kadane(nums2, i, i + n));
        }

        return maxSum;
    }

    private int kadane(int[] nums, int start, int end) {
        int maxFrom, maxTo = start;
        int maxSum = nums[start];
        int from = start;
        int currSum = 0;

        for (int to = start; to < end; to++) {
            if (currSum < 0) {
                from = to;
                currSum = 0;
            }

            currSum += nums[to];
            if (currSum > maxSum) {
                maxSum = currSum;
                maxFrom = from;
                maxTo = to;
            }
        }

        return maxSum;
    }
}

// [2, 3, -4, 2, 3, -4]

// [2, 5,  5, 3, 5,  5]

// kadane(i+1) = 
