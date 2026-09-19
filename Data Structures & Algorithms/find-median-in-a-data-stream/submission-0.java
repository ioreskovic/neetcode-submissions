class MedianFinder {
    private final PriorityQueue<Integer> main;
    private PriorityQueue<Integer> aux;

    public MedianFinder() {
        main = new PriorityQueue<>();
        // not yet used
        aux = null;
    }
    
    public void addNum(int num) {
        // if we searched for median previously, aux exists and we need to sift
        if (aux != null) {
            main.addAll(aux);
            aux = null;
        }

        main.add(num);
    }
    
    public double findMedian() {
        // if we didnt search for median previously, we need to init
        if (aux == null) {
            // we want max element of hald of mins on top
            aux = new PriorityQueue<>(Comparator.reverseOrder());

            int size = main.size();

            // sift half elements
            for (int i = 0; i < size / 2; i++) {
                aux.add(main.poll());
            }
        }
        

        if (aux.size() == main.size()) {
            return (aux.peek() + main.peek()) / 2.0;
        } else {
            return main.peek();
        }
    }
}
