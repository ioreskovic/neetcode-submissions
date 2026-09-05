class Solution {
    record Location(int r, int c) { }

    static class Grid {
        private final int[][] raw;
        final int rows;
        final int cols;

        Grid(int[][] raw) {
            this.raw = raw;
            this.rows = raw.length;
            this.cols = raw[0].length;
        }

        boolean contains(Location loc) {
            return (0 <= loc.r && loc.r < rows) && (0 <= loc.c && loc.c < cols);
        }

        boolean end(Location loc) {
            return (loc.r == rows - 1) && (loc.c == cols - 1);
        }

        boolean accessible(Location loc) {
            return raw[loc.r][loc.c] == 0;
        }

        List<Location> adjacent(Location loc) {
            List<Location> result = new ArrayList<>();

            for (int dr = -1; dr < 2; dr++) {
                for (int dc = -1; dc < 2; dc++) {
                    Location adj = new Location(loc.r + dr, loc.c + dc);
                    if (!contains(adj)) continue;
                    if (loc.equals(adj)) continue;
                    if (!accessible(adj)) continue;
                    result.add(adj);
                }
            }

            return result;
        }
    }

    // !0  !0  !0x
    // !1? !1? !0x
    //  1  1   !0x

    // 2
    public int shortestPathBinaryMatrix(int[][] raw) {
        Grid grid = new Grid(raw);
        Location start = new Location(0, 0);

        if (!grid.accessible(start)) {
            return -1;
        }

        // layer preconditon: accessible & contained
        Deque<Location> layer = new LinkedList<>();
        HashSet<Location> visited = new HashSet<>();
        int clearPathLength = 1;

        layer.offerLast(start);
        visited.add(start);

        while (true) {
            if (layer.isEmpty()) return -1;

            int layerSize = layer.size();
            for (int i = 0; i < layerSize; i++) {
                Location curr = layer.pollFirst();
                if (grid.end(curr)) return clearPathLength;

                grid.adjacent(curr).forEach(adj -> {
                    if (!visited.contains(adj)) {
                        layer.add(adj);
                        visited.add(adj);
                    }
                });
            }

            clearPathLength++;
        }
    }
}