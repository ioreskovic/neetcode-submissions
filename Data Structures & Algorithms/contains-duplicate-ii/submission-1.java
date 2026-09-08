class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // sliding window approach with 2 pointer
        // one adds to seen set, one removes
        // actually, have a map, with counts due to duplicates

        //           R
        // [2, 1, 3, 1], k = 2
        //     L
        // { 2->0, 1->1, 3->1 }

        // start looping R
        // if > k, reduce L
        // check if in set, if yes return, else advance
        // add to set
        // no point in shringking window once R done, because if Set(x, y, z) doesnt contain some elem, Set(x, y) wont either

        if (k < 1) return false;

        Map<Integer, Integer> counts = new HashMap<>();
        for (int r = 0, l = 0; r < nums.length; r++) {
            counts.putIfAbsent(nums[r], 0);

            // consider bounds
            if (Math.abs(r - l) > k) {
                // shrink window by one from left
                counts.computeIfPresent(nums[l++], (__, cnt) -> cnt - 1);
            }

            if (counts.get(nums[r]) > 0) return true;
            counts.computeIfPresent(nums[r], (__, cnt) -> cnt + 1);
        }

        return false;
    }
}