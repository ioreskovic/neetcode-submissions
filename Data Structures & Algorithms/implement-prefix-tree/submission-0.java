class PrefixTree {
    static class Node {
        boolean leaf;
        HashMap<Character, Node> children;

        Node() {
            this.leaf = false;
            this.children = new HashMap<>();
        }

        void word() {
            this.leaf = true;
        }
        
        Node next(char c) {
            return this.children.get(c);
        }
    }

    private Node root;

    public PrefixTree() {
         this.root = new Node();
    }

    public void insert(String word) {
        Node n = this.root;

        for (char c : word.toCharArray()) {
            n = n.children.computeIfAbsent(c, __ -> new Node());
        }

        n.word();
    }

    public boolean search(String word) {
        Node n = this.root;

        for (char c : word.toCharArray()) {
            n = n.next(c);
            if (n == null) return false;
        }

        return n.leaf;
    }

    public boolean startsWith(String prefix) {
        Node n = this.root;

        for (char c : prefix.toCharArray()) {
            n = n.next(c);
            if (n == null) return false;
        }

        return true;
    }
}
