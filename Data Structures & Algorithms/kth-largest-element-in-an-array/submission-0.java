class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.<Integer>naturalOrder().reversed());
        for (int n : nums) {
            heap.add(n);
        }

        for (int i = 1; i < k; i++) {
            heap.poll();
        }

        return heap.peek();
    }
}
