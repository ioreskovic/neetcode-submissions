class Solution {
    public int removeElement(int[] nums, int val) {
        int writeIndex = 0;
        int readIndex = 0;

        while (inBounds(nums, readIndex) && inBounds(nums, writeIndex)) {
            while (inBounds(nums, writeIndex) && nums[writeIndex] != val) {
                writeIndex++;
            }
            readIndex = writeIndex + 1;

            while (inBounds(nums, readIndex) && nums[readIndex] == val) {
                readIndex++;
            }

            if (!swapIfPossible(nums, readIndex, writeIndex)) break;
        }

        return writeIndex;
    }

    private static boolean inBounds(int[] nums, int index) {
        return index >= 0 && index < nums.length;
    }

    private static boolean swapIfPossible(int[] nums, int readIndex, int writeIndex) {
        if (inBounds(nums, readIndex) && inBounds(nums, writeIndex)) {
            final int temp;
            temp = nums[writeIndex];
            nums[writeIndex] = nums[readIndex];
            nums[readIndex] = temp;

            return true;
        } else {
            return false;
        }
    }

    //                  r
    // [0,1,3,0,4,2,2,2], 2 =>
    //            w
}