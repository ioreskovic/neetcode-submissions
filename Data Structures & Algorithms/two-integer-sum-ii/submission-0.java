class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;

        while (l < r) {
            int sum = numbers[l] + numbers[r];
            int comparison = Integer.compare(sum, target);
            
            if (comparison == 0) {
                return new int[] { l + 1, r + 1 };
            } else if (comparison < 0) {
                l++;
            } else {
                r--;
            }
        }

        return null;
    }
}
