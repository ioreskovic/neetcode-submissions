class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // 0-case: initial subset is always empty
        // next subset is take or leave next num-> [1]
        // next subset is take of leave next num -> [2], [1,2]
        // next subset is take ot leave next num -> [3], [1,3], [2,3], [1,2,3]

        List<List<Integer>> acc = new ArrayList<>();
        // acc.add(new ArrayList<>());
        // return subsetsR(nums, 0, acc);

        return subsetsB(nums, 0, new LinkedList<>(), acc);
    }

    private List<List<Integer>> subsetsR(int[] nums, int i, List<List<Integer>> acc) {
        if (i >= nums.length) { // 3 >= 3 +
            return acc;
        }

        List<List<Integer>> expandeds = new ArrayList<>();

        for (List<Integer> subset : acc) { // [], [1], [2], [1,2]
            List<Integer> expanded = new ArrayList(subset); //
            expanded.add(nums[i]); // [3], [1,3], [2,3], [1,2,3]
            expandeds.add(expanded); // [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
        }

        acc.addAll(expandeds);

        return subsetsR(nums, i + 1, acc);
    }

    // [1,2,3], [], []
    //   [2,3], [], []
    //      [3], [], []
    //          [], [], [[]]
    //      [3], [3], [[]]
    //          [], [3], [[], [3]]
    //   [2,3], [2], [[],[3]]
    private List<List<Integer>> subsetsB(int[] nums, int i, LinkedList<Integer> picked, List<List<Integer>> acc) {
        if (i >= nums.length) {
            acc.add(List.copyOf(picked));
            return acc;
        }

        subsetsB(nums, i + 1, picked, acc);
        picked.offerFirst(nums[i]);
        subsetsB(nums, i + 1, picked, acc);
        picked.pollFirst();

        return acc;
    }
}
