class Solution {
    public int maxSubarraySumCircular(int[] nums) {     
        int n = nums.length;   
        int nums2[] = new int[n * 2];
        for (int i = 0; i < n; i++) {
            nums2[i] = nums[i];
            nums2[i + n] = nums[i];
        }

        return kadaneW(nums2, n);
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

    private int kadaneW(int[] arr, int k) {
        int n = arr.length;

        // prefix[i] = arr[0] + ... + arr[i-1]
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        // Monotonic deque: stores indices, prefix values increasing
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(0); // prefix[0] = 0

        int result = arr[0];

        for (int i = 1; i <= n; i++) {
            // Evict indices outside the valid window [i-k, i-1]
            while (!dq.isEmpty() && dq.peekFirst() < i - k) {
                dq.pollFirst();
            }

            // Best candidate ending at i
            result = Math.max(result, prefix[i] - prefix[dq.peekFirst()]);

            // Maintain increasing order: pop back while prefix[back] >= prefix[i]
            while (!dq.isEmpty() && prefix[dq.peekLast()] >= prefix[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
        }

        return result;
    }
}

