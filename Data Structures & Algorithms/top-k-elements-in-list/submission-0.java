class Solution {
    record Countable(int elem, int count) implements Comparable<Countable> {
        @Override
        public int compareTo(Countable other) {
            int countComparison = -Integer.compare(this.count, other.count);
            if (countComparison != 0) return countComparison;

            return -Integer.compare(this.elem, other.elem);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();

        for (int n : nums) {
            cntMap.compute(n, (__, v) -> v == null ? 1 : v + 1);
        }

        ArrayList<Countable> pqInput = new ArrayList<>();
        for (int num : cntMap.keySet()) {
            pqInput.add(new Countable(num, cntMap.get(num)));
        }

        PriorityQueue<Countable> pq = new PriorityQueue<>(pqInput);

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().elem;
        }

        return result;
    }
}
