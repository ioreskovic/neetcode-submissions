class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // we will swap, but lets assume the text1 is the one we are looking for, and text2 is the one we are looking IN for
        // it really does not matter the direction we take, because the problem is symmetrical, e.g.
        // cat in crabt is the same as tac in tbarc
        // so lets start from the beginning
    

        char[] shorter = text1.toCharArray();
        char[] longer =  text2.toCharArray();

        int rows = shorter.length;
        int cols = longer.length;

        int[][] result = new int[rows][cols];

        // set last one
        if (shorter[rows - 1] == longer[cols - 1]) {
            result[rows - 1][cols - 1] = 1;
        } else {
            result[rows - 1][cols - 1] = 0;
        }

        // set last column
        for (int r = rows - 2; r >= 0; r--) {
            if (longer[cols - 1] != shorter[r]) {
                result[r][cols - 1] = result[r + 1][cols - 1];
            } else {
                result[r][cols - 1] = 1;
            }
        }

        // set last row
        for (int c = cols - 2; c >= 0; c--) {
            if (shorter[rows - 1] != longer[c]) {
                result[rows - 1][c] = result[rows - 1][c + 1];
            } else {
                result[rows - 1][c] = 1;
            }
        }

        // now calculate
        for (int r = rows - 2; r >= 0; r--) {
            for (int c = cols - 2; c >= 0; c--) {
                if (shorter[r] != longer[c]) {
                    result[r][c] = Math.max(result[r + 1][c], result[r][c + 1]);
                } else {
                    result[r][c] = result[r + 1][c + 1] + 1;
                }
            }
        }

        return result[0][0];
    }

        // [   3 2 1 1] c
        // [ 2 2 2 1 1] a
        // [ 1 1 1 1 1] t
        //   c c a b t
}
