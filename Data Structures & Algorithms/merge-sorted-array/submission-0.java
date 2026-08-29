class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int writeIdx = nums1.length - 1;
        int read1Idx = m - 1;
        int read2Idx = n - 1;

        while (writeIdx >= 0 && read1Idx >=0 && read2Idx >= 0) {
            int val1 = nums1[read1Idx];
            int val2 = nums2[read2Idx];

            if (val1 > val2) {
                nums1[writeIdx] = val1;
                read1Idx--;
            } else {
                nums1[writeIdx] = val2;
                read2Idx--;
            }

            writeIdx--;
        }

        while (writeIdx >= 0 && read1Idx >= 0) {
            nums1[writeIdx--] = nums1[read1Idx--];
        }

        while (writeIdx >= 0 && read2Idx >= 0) {
            nums1[writeIdx--] = nums2[read2Idx--];
        }

        // [0, 1][0]
        //1 w     2
    }
}