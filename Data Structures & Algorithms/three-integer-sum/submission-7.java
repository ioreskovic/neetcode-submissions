class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        Integer prevCurr = null;
        for (int i = 0; i < nums.length - 2; i++) {
            int a = nums[i];
            if (prevCurr != null && prevCurr == a) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int b = nums[left];
                int c = nums[right];

                int twoSum = b + c;

                if (twoSum == -a) {
                    result.add(List.of(a, b, c));
                    while (left < right && nums[left] == b) left++;
                    while (left < right && nums[right] == c) right--;
                } else if (twoSum < -a) {
                    left++;
                } else {
                    right--;
                }

            }
            prevCurr = a;
        }

        return result;
    }
}
