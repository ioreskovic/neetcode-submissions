class MinStack {
    private final Stack<Integer> underlying;
    private final Stack<Integer> mins;

    public MinStack() {
        underlying = new Stack<>();
        mins = new Stack<>();
    }
    
    public void push(int val) {
        if (mins.isEmpty()) {
            mins.push(val);
        } else {
            mins.push(Math.min(mins.peek(), val));
        }
        underlying.push(val);
    }
    
    public void pop() {
        mins.pop();
        underlying.pop();
    }
    
    public int top() {
        return underlying.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }

    // [1] -> min=1
    // [1, 2] -> min=min(prev.min,2)=min(1,2)=1
    // [1, 2, 0] -> min=min(prev.min,0)=min(1,0)=0
    // [1, 2] -> min=
}
