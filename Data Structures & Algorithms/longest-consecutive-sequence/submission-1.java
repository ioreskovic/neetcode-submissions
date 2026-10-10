class Solution {
    static class UnionFind {
        final HashMap<Integer, Integer> parent;
        final HashMap<Integer, Integer> rank;
        final HashMap<Integer, Integer> size;

        UnionFind() {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();
            this.size = new HashMap<>();
        }

        int find(int n) {
            parent.putIfAbsent(n, n);

            if (n != parent.get(n)) {
                parent.put(n, find(parent.get(n)));
            }

            return parent.get(n);
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if (pa == pb) return false;

            int ra = rank.getOrDefault(pa, 0);
            int rb = rank.getOrDefault(pb, 0);

            int sa = size.getOrDefault(pa, 1);
            int sb = size.getOrDefault(pb, 1);

            if (ra > rb) {
                parent.put(pb, pa);
                size.put(pa, sa + sb);
            } else if (ra < rb) {
                parent.put(pa, pb);
                size.put(pb, sa + sb);
            } else {
                parent.put(pb, pa);
                rank.compute(pa, (__, v) -> v == null ? 0 : v + 1);
                size.put(pa, sa + sb);
            }

            return true;
        }
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        UnionFind uf = new UnionFind();

        for (int n : nums) {
            numSet.add(n);
        }

        for (int n : nums) {
            if (numSet.contains(n - 1)) {
                uf.union(n, n - 1);
            }
        }

        int maxLen = 0;

        for (int n : nums) {
            maxLen = Math.max(maxLen, uf.size.getOrDefault(uf.find(n), 1));
        }

        return maxLen;
    }
}
