class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.heap = new PriorityQueue<Integer>(Comparator.<Integer>naturalOrder().reversed());
        for(int n : nums) {
            this.heap.offer(n);
        }
        this.k = k;
        
    }
    
    public int add(int val) {
        List<Integer> temp = new ArrayList<>();

        // O(logN)
        this.heap.offer(val);

        // O(NlogN)
        for (int i = 1; i < k; i++) {
            temp.add(this.heap.poll());
        }

        int result = this.heap.peek();
        // O(NlogN)
        this.heap.addAll(temp);

        return result;
    }
}
