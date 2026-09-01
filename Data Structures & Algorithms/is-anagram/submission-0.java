class Solution {
    public boolean isAnagram(String s, String t) {
        final var countMap = new HashMap<Character, Integer>();

        for (char c : s.toCharArray()) {
            countMap.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
        }

        for (char c : t.toCharArray()) {
            countMap.compute(c, (k, v) -> (v == null) ? -1 : v - 1);
        }

        return !countMap.values().stream().anyMatch(cnt -> cnt != 0);
    }
}
