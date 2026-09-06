class Solution {
    public int uniquePaths(int rows, int cols) {
        // formula: result[r, c] = result[r, c + 1] + result[r + 1, c]

        int[][] result =  new int[rows][cols];

        for (int c = 0; c < cols; c++) {
            result[rows - 1][c] = 1;
        }
        for (int r = 0; r < rows; r++) {
            result[r][cols - 1] = 1;
        }

        for (int r = rows - 2; r >= 0; r--) {
            for (int c = cols - 2; c >= 0; c--) {
                result[r][c] = result[r][c + 1] + result[r + 1][c];
            }
        }

        return result[0][0];
    }
}
