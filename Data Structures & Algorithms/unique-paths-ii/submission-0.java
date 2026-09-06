class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        // formula: result[r, c] = result[r, c + 1] + result[r + 1, c]
        // caveat: if grid[r, c] = 0, result[r, c] = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] result = new int[rows][cols];

        // check if result accessible
        if (grid[rows - 1][cols - 1] > 0) {
            result[rows - 1][cols - 1] = 0;
        } else {
            result[rows - 1][cols - 1] = 1;
        }

        // build last row
        for (int c = cols - 2; c >= 0; c--) {
            // if I am blocked, nothing can come thru me
            if (grid[rows - 1][c] > 0) {
                result[rows - 1][c] = 0;
            } else {
                result[rows - 1][c] = result[rows - 1][c + 1];
            }
        }

        // build last column
        for (int r = rows - 2; r >= 0; r--) {
            // if I am blocked, nothing can come thru me
            if (grid[r][cols - 1] > 0) {
                result[r][cols - 1] = 0;
            } else {
                result[r][cols - 1] = result[r + 1][cols - 1];
            }
        }

        // now do everything else starting at 1st uncalculated
        for (int r = rows - 2; r >= 0; r--) {
            for (int c = cols - 2; c >= 0; c--) {
                // if I am blocked, nothing can come thru me
                if (grid[r][c] > 0) {
                    result[r][c] = 0;
                } else {
                    result[r][c] = result[r][c + 1] + result[r + 1][c];
                }
            }
        }

        return result[0][0];
    }
}