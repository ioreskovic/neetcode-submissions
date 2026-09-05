class Solution {
    // if i landed on an island, i need to do mark it as fully explored - dfs
    // once i do, i return that i fonud and marked an entire island

    // if i landed on water, i look around me


    public int numIslands(char[][] grid) {
        return countIslands(new Grid(grid), new HashSet<>());
    }

    private int countIslands(Grid grid, HashSet<Location> visited) {
        int islandCount = 0;
        for (int r = 0; r < grid.rows; r++) {
            for (int c = 0; c < grid.cols; c++) {
                Location loc = new Location(r, c);
                if (!grid.contains(loc)) continue;
                if (visited.contains(loc)) continue;
                if (grid.isLand(loc)) {
                    exploreIsland(grid, loc, visited);
                    islandCount++;
                } else {
                    visited.add(loc);
                }
            }
        }

        return islandCount;
    }

    private void exploreIsland(Grid grid, Location loc, HashSet<Location> visited) {
        if (!grid.contains(loc)) return;
        if (visited.contains(loc)) return;
        if (!grid.isLand(loc)) return;

        visited.add(loc);
        loc.neighbours().forEach(n -> exploreIsland(grid, n, visited));
    }

    record Location(int r, int c) {
        List<Location> neighbours() {
            return List.of(
                new Location(r + 1, c),
                new Location(r - 1, c),
                new Location(r, c + 1),
                new Location(r, c - 1)
            );
        }
    }

    static class Grid {
        private final char[][] raw;
        private final int rows;
        private final int cols;

        Grid(char[][] raw) {
            this.raw = raw;
            this.rows = raw.length;
            this.cols = raw[0].length;
        }

        boolean contains(Location loc) {
            return (0 <= loc.r && loc.r < rows) && (0 <= loc.c && loc.c < cols);
        }

        boolean isLand(Location loc) {
            return raw[loc.r][loc.c] == '1';
        }
    }
}
