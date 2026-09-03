class Solution {
    public int[] countBits(int n) {
        int[] solution = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                solution[i] = 0;
            } else if (i == 1) {
                solution[i] = 1;
            } else if ((i % 2) == 0) {
                solution[i] = solution[i / 2];
            } else {
                solution[i] = solution[i / 2] + 1;
            }
        }

        return solution;
    }

    // 00000000 - 0

    // 00000001 - 1

    // 00000010 - 1
    // 00000011 - 2

    // 00000100 - 1
    // 00000101 - 2
    // 00000110 - 2
    // 00000111 - 3

    // 00001000 - 1
    // 00001001 - 2
    // 00001010 - 2
    // 00001011 - 3
    // 00001100 - 2
    // 00001101 - 3
    // 00001110 - 3
    // 00001111 - 3

    // 00010000 - 1

    // even number x has the same amount as x / 2
    // even number y has the same amount as y / 2 + 1


}
