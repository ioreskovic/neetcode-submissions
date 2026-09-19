class MedianFinder {
    private final PriorityQueue<Integer> biggerHalf;
    private final PriorityQueue<Integer> smallerHalf;

    public MedianFinder() {
        // minHeap, smallest of biggers always on top
        biggerHalf = new PriorityQueue<>();

        // maxHead, biggest of smallest always on top
        smallerHalf = new PriorityQueue<>(Comparator.reverseOrder());
    }
    
    public void addNum(int num) {
        // Push to smallerHalf first, then balance to biggerHalf
        smallerHalf.add(num);
        biggerHalf.add(smallerHalf.poll());

        // Maintain the invariant: biggerHalf size == smallerHalf size or biggerHalf size == smallerHalf size + 1
        if (smallerHalf.size() < biggerHalf.size() - 1) {
            smallerHalf.add(biggerHalf.poll());
        }
    }
    
    public double findMedian() {
        if (smallerHalf.size() == biggerHalf.size()) {
            return (smallerHalf.peek() + biggerHalf.peek()) / 2.0;
        } else {
            return biggerHalf.peek();
        }
    }
}
