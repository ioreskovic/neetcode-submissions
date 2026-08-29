class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        int end = rows * cols - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int i = mid / cols;
            int j = mid % cols;

            int midVal = matrix[i][j];

            if (target < midVal) {
                end = end - 1;
            } else if (target > midVal) {
                start = start + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
