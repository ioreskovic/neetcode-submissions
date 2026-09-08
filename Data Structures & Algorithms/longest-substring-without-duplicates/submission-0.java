class Solution {
    //             R
    // z x y z y x w
    //       L
    //
    // {z,y,x,w}, 3

    //         R
    // x x x x
    //       L
    //
    // {x}, 1
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> seen = new HashSet<>();
        int maxLen = 0;

        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (!seen.contains(c)) {
                seen.add(c);
                maxLen = Math.max(maxLen, seen.size());
            } else {
                while (s.charAt(l) != c) {
                    seen.remove(s.charAt(l++));
                }
                seen.remove(s.charAt(l++));
                seen.add(c);
            }
        }

        return maxLen;
    }
}
