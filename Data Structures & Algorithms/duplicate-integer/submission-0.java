class Solution {
    public boolean hasDuplicate(int[] nums) {
        final var countMap = new HashSet<Integer>();
        for (int n : nums) {
            if (countMap.contains(n)) {
                return true;
            }
            countMap.add(n);
        }
        return false;
    }
}