class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Integer.MIN_VALUE;

        for (int i : piles) {
            if (i > high) {
                high = i;
            }
        }

        int bestMid = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int eatingSpeedResult = eatingSpeed(piles, mid, h);

            // if i am eating too slow, my hours are > h, speed up
            if (eatingSpeedResult < 0) {
                low = mid + 1;
            } 
            // i am eating too fast, my hours are < h, slow down
            else {
                high = mid - 1;
                if (mid < bestMid) {
                    bestMid = mid;
                }
            }
        }

        return bestMid;
    }

    private int eatingSpeed(int[] piles, int currentSpeed, int maxTime) {
        int totalTime = 0;
        for (int numBananas : piles) {
            int m = numBananas / currentSpeed;
            int d = numBananas % currentSpeed;

            totalTime += m + (d > 0 ? 1 : 0);
        }

        if (totalTime > maxTime) {
            return -1;
        } else if (totalTime < maxTime) {
            return 1;
        } else {
            return 0;
        }
    }
}
