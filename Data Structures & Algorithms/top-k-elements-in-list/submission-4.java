class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int numBucketCount = 2001;
        int offset = 1000;
        int[] numBuckets = new int[numBucketCount];
        int maxFreq = 0;
        for (int num : nums) {
            var idx = num + offset;
            numBuckets[idx]++;
            maxFreq = Math.max(maxFreq, numBuckets[idx]);
        }

        ArrayList<ArrayList<Integer>> freqBuckets = new ArrayList<>(maxFreq + 1);
        for (int i = 0; i <= maxFreq; i++) {
            freqBuckets.add(new ArrayList<>());
        }

        for (int i = 0; i < numBucketCount; i++) {
            var num = i - offset;
            var freq = numBuckets[i];
            freqBuckets.get(freq).add(num);
        }

        int[] result = new int[k];
        int writeIdx = 0;
        int readIdx = maxFreq;

        while (maxFreq > 0 && writeIdx < k) {
            var freqArr = freqBuckets.get(maxFreq--);
            for (int i = 0; i < freqArr.size() && writeIdx < k; i++) {
                result[writeIdx++] = freqArr.get(i);
            }
        }

        return result;
    }
}
