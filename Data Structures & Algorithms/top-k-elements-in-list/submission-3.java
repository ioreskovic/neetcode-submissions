class Solution {
    record Countable(int elem, int count) implements Comparable<Countable> {
        @Override
        public int compareTo(Countable other) {
            int countComparison = Integer.compare(this.count, other.count);
            if (countComparison != 0) return countComparison;

            return Integer.compare(this.elem, other.elem);
        }
    }

    public int[] topKFrequent_pq(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();

        for (int n : nums) {
            cntMap.compute(n, (__, v) -> v == null ? 1 : v + 1);
        }

        PriorityQueue<Countable> pq = new PriorityQueue<>();
        for (int num : cntMap.keySet()) {
            var curr = new Countable(num, cntMap.get(num));

            pq.offer(curr);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().elem;
        }

        return result;
    }

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
