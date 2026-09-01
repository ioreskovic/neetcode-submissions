class Solution {
    public int[] twoSum(int[] nums, int target) {
        final var indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            final var maybeMatch = indexMap.get(target - n);
            if (maybeMatch != null) {
                return new int[] { maybeMatch, i };
            }

            indexMap.put(n, i);
        }

        return null;
    }

    // [3, 4, 5, 6]
    // {3 -> 0}, 

    // s=11
    // [3, 4, 5, 6]
    //  0 -> {}(8) x
    //  1 -> {3}(7) x
    //  2 -> {3,4}(6) x
    //  3 -> {3,4,5}(5) +


    // [5, 5]
    //  0 -> {}(5)
    //  1 -> {5}(5) + set.get(opp).idx, i

}
