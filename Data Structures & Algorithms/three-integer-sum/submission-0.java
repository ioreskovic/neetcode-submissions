class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();

        HashSet<Integer> previous = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];

                int twoSum = a + b;
                if (previous.contains(-twoSum)) {
                    var subResult = new ArrayList<Integer>();
                    subResult.add(a);
                    subResult.add(b);
                    subResult.add(-twoSum);
                    Collections.sort(subResult);
                    result.add(subResult);
                }
            }
            previous.add(a);
        }

        return new ArrayList(result);
    }
}
