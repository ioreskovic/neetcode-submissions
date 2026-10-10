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

            var pn = parent.get(n);

            if (n != pn) {
                parent.put(n, find(pn));
            }

            return parent.get(n);
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if (pa == pb) return false;

            int ra = rank.getOrDefault(pa, 0);
            int rb = rank.getOrDefault(pb, 0);

            if (ra > rb) {
                parent.put(pb, pa);
            } else if (ra < rb) {
                parent.put(pa, pb);
            } else {
                parent.put(pb, pa);
                rank.compute(pa, (__, r) -> r == null ? 0 : r + 1);
            }

            return true;
        }
    }

    record Edge(int a, int b) {}

    public int countComponents(int n, int[][] edges) {
        var uf = new UnionFind();
        int count = n;

        for (int[] raw : edges) {
            var edge = new Edge(raw[0], raw[1]);
            if (uf.union(edge.a, edge.b)) {
                count--;
            }
        }

        return count;
    }
}
