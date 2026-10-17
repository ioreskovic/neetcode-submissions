class Solution {
    public boolean isValidSudoku(char[][] board) {
        List<HashSet<Integer>> rowConstraints = new ArrayList();
        List<HashSet<Integer>> colConstraints = new ArrayList();
        List<HashSet<Integer>> boxConstraints = new ArrayList();

        for (int i = 0; i < 9; i++) {
            rowConstraints.add(new HashSet<>());
            colConstraints.add(new HashSet<>());
            boxConstraints.add(new HashSet<>());
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int n = board[r][c];
                if (n == '.') continue;
                var rowConstraint = rowConstraints.get(rowOf(r, c));
                if (rowConstraint.contains(n)) return false;

                var colConstraint = colConstraints.get(colOf(r, c));
                if (colConstraint.contains(n)) return false;

                var boxConstraint = boxConstraints.get(boxOf(r, c));
                if (boxConstraint.contains(n)) return false;

                rowConstraint.add(n);
                colConstraint.add(n);
                boxConstraint.add(n);
            }
        }

        return true;
    }

    int rowOf(int r, int c) {
        return r;
    }

    int colOf(int r, int c) {
        return c;
    }

    int boxOf(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
