class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> acc = new ArrayList<>();
        heaps(nums, nums.length, acc);
        return acc;
    }

    private void heaps(int[] nums, int k, List<List<Integer>> acc) {
        if (k == 1) {
            acc.add(Arrays.stream(nums).boxed().toList());
            return;
        }

        heaps(nums, k - 1, acc);
        for (int i = 0; i < k - 1; i++) {
            if (k % 2 == 0) {
                swap(nums, i, k - 1);
            } else {
                swap(nums, 0, k - 1);
            }
            heaps(nums, k - 1, acc);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
