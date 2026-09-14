class Solution {
    public int maxArea(int[] heights) {
        int l = 0;
        int r = heights.length - 1;

        int maxVolume = volume(heights, l, r);

        while (l < r) {
            int currVolume = volume(heights, l, r);
            maxVolume = Math.max(maxVolume, currVolume);

            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }

        return maxVolume;
    }

    private int volume(int[] heights, int l, int r) {
        return Math.min(heights[l], heights[r]) * Math.abs(r - l);
    }
}
