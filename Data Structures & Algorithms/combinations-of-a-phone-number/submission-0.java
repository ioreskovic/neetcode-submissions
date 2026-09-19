class Solution {
    private static Map<Character, List<Character>> t9 = Map.of(
        '2', List.of('a', 'b', 'c'),
        '3', List.of('d', 'e', 'f'),
        '4', List.of('g', 'h', 'i'),
        '5', List.of('j', 'k', 'l'),
        '6', List.of('m', 'n', 'o'),
        '7', List.of('p', 'q', 'r', 's'),
        '8', List.of('t', 'u', 'v'),
        '9', List.of('w', 'x', 'y', 'z')
    );

    public List<String> letterCombinations(String digits) {
        List<String> acc = new ArrayList<>();
        decoded(digits, 0, new StringBuilder(), acc);
        return acc;
    }

    private void decoded(String digits, int i, StringBuilder prefix, List<String> acc) {
        if (i >= digits.length()) {
            if (prefix.length() > 0) {
                acc.add(prefix.toString());
            }
            return;
        }

        for (char c : t9.getOrDefault(digits.charAt(i), List.of())) {
            prefix.append(c);
            decoded(digits, i + 1, prefix, acc);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
