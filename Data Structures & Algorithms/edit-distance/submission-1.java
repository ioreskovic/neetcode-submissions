class Solution {
    // ground rules:
    // ed(,)=0
    // ed(x,)=1
    // ed(,x)=1
    // ed(x,x)=0
    // ed(x,y)=1
    // ed(x::xs, y::ys) = min(
    //   1 + ed(xs, y::ys) // delete head
    //   1 + ed(x::xs, ys) // insert y at head, which is transformed to this
    //   (x == y) + ed(xs, ys) // swap if needed
    // )
    public int minDistance(String word1, String word2) {
        int ed[][] = new int[word1.length() + 1][word2.length() + 1];
        ed[0][0] = 0;
        for (int i = 0; i < word1.length(); i++) {
            ed[i + 1][0] = i + 1;
        }
        for (int i = 0; i < word2.length(); i++) {
            ed[0][i + 1] = i + 1;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int same = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                ed[i][j] = Math.min(
                    1 + Math.min(ed[i - 1][j], ed[i][j - 1]),
                    same + ed[i - 1][j - 1]
                );
            }
        }

        return ed[word1.length()][word2.length()];
    }
}
