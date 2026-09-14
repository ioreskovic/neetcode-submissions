class Solution {
    public int characterReplacement(String s, int k) {
        // result [1, s.length]
        //
        //  l
        // AAABABBBB - 1
        //      r
        // 
        // { a->3, b->2 }, a, 5, 

        HashMap<Character, Integer> freq = new HashMap<>();
        char mostFrequent = s.charAt(0);
        freq.put(mostFrequent, 1);
        int maxLen = 1;
        
        for (int l = 0, r = 1; r < s.length(); r++) {
            char curr = s.charAt(r);

            int mFreq = freq.getOrDefault(mostFrequent, 0);
            int cFreq = freq.getOrDefault(curr, 0) + 1;
            freq.put(curr, cFreq);
            if (cFreq > mFreq) mostFrequent = curr;

            while ((r - l + 1) - freq.getOrDefault(mostFrequent, 0) > k) {
                freq.computeIfPresent(s.charAt(l), (__, v) -> v - 1);
                mostFrequent = findMostFrequent(freq);
                l++;
            }

            maxLen = Math.max(maxLen, (r - l + 1));
        }

        return maxLen;
    }

    private char findMostFrequent(HashMap<Character, Integer> freq) {
        char mostFrequent = 0;
        int maxFreq = Integer.MIN_VALUE;

        for (char c : freq.keySet()) {
            int f = freq.get(c);
            if (f > maxFreq) {
                maxFreq = f;
                mostFrequent = c;
            }
        }

        return mostFrequent;
    }
}
