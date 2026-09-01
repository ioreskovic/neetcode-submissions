class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Comparator.<Integer>naturalOrder().reversed());
        for (int s : stones) {
            heap.add(s);
        }

        while (heap.size() > 1) {
            int x = heap.poll();
            int y = heap.poll();

            int diff = Math.abs(x - y);
            if (diff > 0) {
                heap.offer(diff);
            }
        }

        if (heap.isEmpty()) {
            return 0;
        } else {
            return heap.peek();
        }
    }

    // 1
}
