class Solution {
    class PrefixTree {
        static class Node {
            String word;
            HashMap<Character, Node> children;

            Node() {
                this.word = null;
                this.children = new HashMap<>();
            }

            void word(String s) {
                this.word = s;
            }
            
            Node next(char c) {
                return this.children.get(c);
            }
        }

        Node root;

        public PrefixTree() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node n = this.root;

            for (char c : word.toCharArray()) {
                n = n.children.computeIfAbsent(c, __ -> new Node());
            }

            n.word(word);
        }

        public boolean search(String word) {
            Node n = this.root;

            for (char c : word.toCharArray()) {
                n = n.next(c);
                if (n == null) return false;
            }

            return n.word != null;
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

    static record Bounds(int minRow, int maxRow, int minCol, int maxCol) {
        boolean valid(Cell cell) {
            return (minRow <= cell.r && cell.r <= maxRow) && (minCol <= cell.c && cell.c <= maxCol);
        }
    }

    static record Cell(int r, int c) {
        List<Cell> next(Bounds bounds, HashSet<Cell> seen) {
            return List.of(
                new Cell(r + 1, c),
                new Cell(r - 1, c),
                new Cell(r, c + 1),
                new Cell(r, c - 1)
            );
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        final var trie = new PrefixTree();
        final var bounds = new Bounds(0, board.length - 1, 0, board[0].length - 1);

        for (String word : words) {
            trie.insert(word);
        }

        Set<String> result = new HashSet<>();

        for (int r = bounds.minRow; r <= bounds.maxRow; r++) {
            for (int c = bounds.minCol; c <= bounds.maxCol; c++) {
                iterate(board, bounds, trie.root, new Cell(r, c), new HashSet<>(), result);
            }
        }

        return result.stream().collect(Collectors.toList());
    }

    private void iterate(char[][] board, Bounds bounds, PrefixTree.Node node, Cell cell, HashSet<Cell> seen, Set<String> result) {
        if (seen.contains(cell)) return;
        seen.add(cell);

        char c = board[cell.r][cell.c];
        var cNode = node.children.get(c);

        if (cNode == null) {
            seen.remove(cell);
            return;
        }
        
        if (cNode.word != null) result.add(cNode.word);

        for (Cell next : cell.next(bounds, seen)) {
            if (seen.contains(next)) continue;
            if (!bounds.valid(next)) continue;

            iterate(board, bounds, cNode, next, seen, result);
        }

        seen.remove(cell);
    }
}
