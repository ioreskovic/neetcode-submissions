class Solution {
    record Location(int r, int c) {
        boolean inbound(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            return (0 <= r && r < rows) && (0 <= c && c < cols);
        }

        boolean land(int[][] grid) {
            return grid[r][c] > 0;
        }

        List<Location> neighbours() {
            return List.of(
                new Location(r + 1, c),
                new Location(r - 1, c),
                new Location(r, c + 1),
                new Location(r, c - 1)
            );
        }
    }

    // counting islands keeping track of max area
    public int maxAreaOfIsland(int[][] grid) {
        HashSet<Location> visited = new HashSet<>();
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Location loc = new Location(r, c);
                
                if (!loc.inbound(grid)) continue;
                if (visited.contains(loc)) continue;
                if (loc.land(grid)) {
                    int area = exploreIsland(grid, loc, visited);
                    maxArea = Math.max(maxArea, area);
                } else {
                    visited.add(loc);
                }
            }
        }

        return maxArea;
    }

    private int exploreIsland(int[][] grid, Location loc, HashSet<Location> visited) {
        if (!loc.inbound(grid)) return 0;
        if (visited.contains(loc)) return 0;
        if (!loc.land(grid)) return 0;

        visited.add(loc);
        return 1 + loc.neighbours().stream().mapToInt(n -> exploreIsland(grid, n, visited)).sum();
    }
}
