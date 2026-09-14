class Solution {
    //  l
    // [1, 1, 2]
    //     r
    // maxLen = 1
    // comparison = 0
    public int maxTurbulenceSize(int[] arr) {
        
        int maxLen = 1;
        Integer comparison = 0;

        for (int l = 0, r = 1; r < arr.length; r++) {
            int currComparison = Integer.compare(arr[r], arr[r - 1]);
            if (currComparison == 0) {
                l = r;
            } else if (currComparison == comparison) {
                l = r - 1;
            } else {
                comparison = currComparison;
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }

        return maxLen;
    }
}