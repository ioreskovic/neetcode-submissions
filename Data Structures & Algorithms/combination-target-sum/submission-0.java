class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        // All combinations satisfying traveral is usually a backtraccking problem, nums are positive, makes no sense otherwise
        // Proof: [0], [-1,1]. So each addition brings us closer to the sum.
        //
        // at each step:
        //   if ihave reached targetSum, record combinatin
        //   if i have exceeded target sum, return
        //   else
        //      for every num:
        //        recurse(nums, push(num), target - num);
        //        pop(num)
        
        return recurse(nums, 0, target, new LinkedList<>(), new ArrayList<>());
    }

    private List<List<Integer>> recurse(int[] nums, int i, int toTarget, LinkedList<Integer> stack, List<List<Integer>> acc) {
        if (toTarget == 0) {
            acc.add(List.copyOf(stack));
            return acc;
        }

        if (toTarget < 0) {
            return acc;
        }

        if (i >= nums.length) {
            return acc;
        }

            // havent taken it
            recurse(nums, i + 1, toTarget, stack, acc);

            // have taken it, i might want to take it again
            int num = nums[i];
            stack.offerFirst(num);
            recurse(nums, i, toTarget - num, stack, acc);

            // backtrack
            stack.pollFirst();

        return acc;        
    }
}
