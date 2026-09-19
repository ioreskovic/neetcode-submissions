class Solution {
    // 1 2 2 2 3
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        return dubSub(nums, 0, new LinkedList<>(), new ArrayList<>());
    }

    private List<List<Integer>> dubSub(int[] nums, int i, LinkedList<Integer> prefix, List<List<Integer>> acc) {
        if (i >= nums.length) {
            acc.add(List.copyOf(prefix));
            return acc;
        }

        // 0, [], []
        // 1, [1], []
        // 2, [1,2], []
        // ...
        // 4, [1,2,2,2], []
        // 5, [1,2,2,2,3], [[1,2,2,2,3]]
        // 5, [1,2,2,2]

        // include
        prefix.offerLast(nums[i]);
        dubSub(nums, i + 1, prefix, acc);
        prefix.pollLast();
        int j = i;
        // not include at all
        for (j = i; j + 1 < nums.length && nums[j] == nums[j + 1]; j++) { }
        dubSub(nums, j + 1, prefix, acc);

        return acc;
    }
}
