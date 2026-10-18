class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> s1Freq = new HashMap<>();
        Map<Character, Integer> s2Freq = new HashMap<>();

        int window = s1.length();

        for (int i = 0; i < window; i++) {
            var c1 = s1.charAt(i);
            var c2 = s2.charAt(i);

            s1Freq.compute(c1, (__, v) -> increment(v));
            s2Freq.compute(c2, (__, v) -> increment(v));
        }


        int i = 1;
        int j = i + window - 1;

        while (j < s2.length()) {
            // state from previous computation
            if (s1Freq.equals(s2Freq)) return true;

            var cOld = s2.charAt(i - 1);
            var cNew = s2.charAt(j);

            s2Freq.compute(cOld, (__, v) -> decrementOrNull(v));
            s2Freq.compute(cNew, (__, v) -> increment(v));

            i++;
            j++;
        }

        return (s1Freq.equals(s2Freq));

    }

    private static Integer decrementOrNull(Integer old) {
        if (old == null) return null;
        if (old == 1) return null;
        return old - 1;
    }

    private static Integer increment(Integer old) {
        if (old == null) return 1;
        return old + 1;
    }
}
