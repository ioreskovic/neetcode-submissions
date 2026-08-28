class BrowserHistory {
    private Stack<String> prev;
    private String curr;
    private Stack<String> next;

    public BrowserHistory(String homepage) {
        this.prev = new Stack<>();
        this.curr = homepage;
        this.next = new Stack<>();
    }
    
    public void visit(String url) {
        this.prev.push(curr);
        this.curr = url;
        this.next.clear();
    }
    
    public String back(int steps) {
        for (int i = 0; i < steps && !this.prev.isEmpty(); i++) {
            this.next.push(this.curr);
            this.curr = this.prev.pop();
        }
        return this.curr;
    }
    
    public String forward(int steps) {
        for (int i = 0; i < steps && !this.next.isEmpty(); i++) {
            this.prev.push(this.curr);
            this.curr = this.next.pop();
        }
        return this.curr;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */