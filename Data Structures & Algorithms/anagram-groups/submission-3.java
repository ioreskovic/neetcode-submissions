class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            var hash = freq(s);
            groups.computeIfAbsent(hash, __ -> new ArrayList<>()).add(s);
        }
        
        return new ArrayList(groups.values());
    }

    private String freq(String word) {
        var alphabetSize = 'z' - 'a' + 1;
        int[] buckets = new int[alphabetSize];

        for (char c : word.toCharArray()) {
            buckets[c - 'a']++;
        }

        return Arrays.toString(buckets);
    }
}
