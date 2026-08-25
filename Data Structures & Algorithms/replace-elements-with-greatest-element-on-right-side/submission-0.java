class Solution {
    public int[] replaceElements(int[] arr) {
        int maxRight = -1;
        int idx = arr.length - 1;

        while (idx >= 0) {
            int readValue = arr[idx];
            arr[idx] = maxRight;
            maxRight = Math.max(maxRight, readValue);
            idx--;
        }

        return arr;
    }

    //    5
    // [2,5,3,2,2,-1] 5
    //    w

    // max(r0, rs)
    // max(r0, max(r1, rs))


}