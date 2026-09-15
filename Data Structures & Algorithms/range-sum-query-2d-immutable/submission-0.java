class NumMatrix {
    private final int[][] matrixSum;

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        this.matrixSum = new int[rows + 1][cols + 1];
        for (int c = 0; c <= cols; c++) {
            matrixSum[0][c] = 0;
        }
        for (int r = 0; r <= rows; r++) {
            matrixSum[r][0] = 0;
        }

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                matrixSum[r][c] = matrix[r - 1][c - 1] + matrixSum[r][c - 1] + matrixSum[r - 1][c] - matrixSum[r - 1][c - 1];
                System.out.print(matrixSum[r][c]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }
    
    public int sumRegion(int r1, int c1, int r2, int c2) {
        return matrixSum[r2 + 1][c2 + 1] - matrixSum[r1][c2 + 1] - matrixSum[r2 + 1][c1] + matrixSum[r1][c1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */