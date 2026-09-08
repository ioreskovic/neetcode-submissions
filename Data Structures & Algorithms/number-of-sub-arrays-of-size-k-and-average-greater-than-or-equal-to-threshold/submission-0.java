class Solution {
    // [2,2,2,2,5,5,5,8], k = 3, t = 4
    // roll the sum of the subarray:
    //   if not full, sum+=r
    //   when full, inc if full
    //   if full, sum-=l
    //
    //      R
    // [2,2,2,2,5,5,5,8], s=6, c=0
    //  L
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count = 0;
        int currSum = 0;

        for (int l = 0, r = 0; r < arr.length; r++) {
            currSum += arr[r];

            if (r - l + 1>= k) {
                if (currSum >= (threshold * k)) count++;
                currSum -= arr[l++];
            }
        }

        return count;
    }
}