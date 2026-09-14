class Solution {
    public int trap(int[] height) {
        int len = height.length;

        int[] lookBehind = new int[len];
        int[] lookAhead = new int[len];

        lookBehind[0] = 0;
        for (int i = 1; i < len; i++) {
            lookBehind[i] = Math.max(lookBehind[i - 1], height[i - 1]);
        }

        lookAhead[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            lookAhead[i] = Math.max(lookAhead[i + 1], height[i + 1]);
        }

        int sum = 0;

        for (int i = 0; i < len; i++) {
            int maxHeight = Math.min(lookBehind[i], lookAhead[i]);
            int waterContent = Math.max(maxHeight - height[i], 0);
            sum += waterContent;
        }

        return sum;
    }
}
