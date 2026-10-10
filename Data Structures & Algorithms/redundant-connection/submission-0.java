class Solution {
    static class UnionFind {
        private final HashMap<Integer, Integer> parent;
        private final HashMap<Integer, Integer> rank;

        UnionFind() {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();
        }

        int find(int n) {
            parent.putIfAbsent(n, n);

            if (n != parent.get(n)) {
                parent.put(n, find(parent.get(n)));
            }

            return parent.get(n);
        }

        boolean union(int a, int b) {
            rank.putIfAbsent(a, 0);
            rank.putIfAbsent(b, 0);

            int pa = find(a);
            int pb = find(b);

            if (pa == pb) return false;

            int ra = rank.get(pa);
            int rb = rank.get(pb);

            if (ra > rb) {
                parent.put(pb, pa);
            } else if (ra < rb) {
                parent.put(pa, pb);
            } else {
                parent.put(pb, pa);
                rank.compute(pa, (__, v) -> v + 1);
            }

            return true;
        }
    }

    static record Edge(int a, int b) { }

    public int[] findRedundantConnection(int[][] edges) {
        LinkedList<Edge> cycleStack = new LinkedList<>();
        UnionFind uf = new UnionFind();

        for (int[] rawEdge : edges) {
            var edge = new Edge(rawEdge[0], rawEdge[1]);
            if (!uf.union(edge.a, edge.b)) {
                cycleStack.offerLast(edge);
            }
        }

        var offender = cycleStack.peekLast();

        return new int[] { offender.a, offender.b };
    }
}
