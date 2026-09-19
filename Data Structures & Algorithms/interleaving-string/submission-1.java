class Solution {
    private Boolean[][] memo;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return loop(
            s1, s2, s3,
            s1.length() - 1, s2.length() - 1, s3.length() - 1
        );
    }

    // i1, i2, i3 represent how many items is left in each
    private boolean loop(String s1, String s2, String s3, int i1, int i2, int i3) {

        // if we have exahusted all successfully
        if (i1 == -1 && i2 == -1 && i3 == -1) return true;
        if (memo[i1 + 1][i2 + 1] != null) return memo[i1 + 1][i2 + 1];
        // if any match, then true

        char c1 = chatAtOrSentinel(s1, i1);
        char c2 = chatAtOrSentinel(s2, i2);
        char c3 = chatAtOrSentinel(s3, i3);

        boolean result13 = (c1 == c3) && loop(s1, s2, s3, i1 - 1, i2, i3 - 1);
        boolean result23 = (c2 == c3) && loop(s1, s2, s3, i1, i2 - 1, i3 - 1);
        boolean result = result13 || result23;
        memo[i1 + 1][i2 + 1] = result;
        return result;
    }

    private char chatAtOrSentinel(String s, int i) {
        if ((0 <= i) && (i < s.length())) return s.charAt(i);
        return 0;
    }
}
