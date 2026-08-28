class MyStack {
    private Deque<Integer> first;
    private Deque<Integer> second;

    private Deque<Integer> temp;

    public MyStack() {
        this.first = new LinkedList<Integer>();
        this.second = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        this.first.offerLast(x);
    }
    
    public int pop() {
        sift(this.first.size() - 1);
        var result = this.first.removeFirst();
        swap();
        return result;
    }
    
    public int top() {
        sift(this.first.size() - 1);
        var result = this.first.removeFirst();
        this.second.offerLast(result);
        swap();
        return result;
    }
    
    public boolean empty() {
        return first.isEmpty();
    }

    private void swap() {
        this.temp = this.first;
        this.first = this.second;
        this.second = this.temp;
    }

    private void sift(int n) {
        for (int i = 0; i < n; i++) {
            this.second.offerLast(this.first.removeFirst());
        }
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */