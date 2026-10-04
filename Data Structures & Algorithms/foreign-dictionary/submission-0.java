class Solution {
    sealed interface Info permits Before, Invalid {}
    static record Before(char before, char after) implements Info {}
    static record Invalid() implements Info {}

    public String foreignDictionary(String[] words) {
      if (words.length == 1) return toAlphabetString(alphabet(new HashSet<>(), words[0]));
      
      Set<Character> alphabet = new HashSet<>();
      List<Info> infos = new ArrayList<>();
      Map<Character, HashSet<Character>> afterOthers = new HashMap<>();

      for (int i = 0; i < words.length - 1; i++) {
        var w1 = words[i];
        var w2 = words[i + 1];
        alphabet(alphabet, w1);
        alphabet(alphabet, w2);

        var info = wordInfo(w1, w2);

        switch (info.orElse(null)) {
            case null -> { }
            case Invalid() -> { return ""; }
            case Before(char before, char after) -> afterOthers.computeIfAbsent(after, k -> new HashSet<>()).add(before);
        }
      }

      var seen = new HashSet<Character>();
      var path = new HashSet<Character>();
      var result = new StringBuilder();
      var stop = new AtomicBoolean(false);

      for (char a : alphabet) {
        topoSort(a, afterOthers, seen, path, result, stop);
      }

      if (stop.get()) return "";
      return result.toString();
    }

    private void topoSort(char a, Map<Character, HashSet<Character>> next, HashSet<Character> seen, HashSet<Character> path, StringBuilder acc, AtomicBoolean stop) {
        if (stop.get()) return;
        if (path.contains(a)) {
            stop.set(true);
            return;
        }
        if (seen.contains(a)) return;

        seen.add(a);
        path.add(a);

        for (char n : next.getOrDefault(a, new HashSet<>())) {
            topoSort(n, next, seen, path, acc, stop);
        }

        path.remove(a);
        acc.append(a);
    }

    private Optional<Info> charInfo(Optional<Character> c1, Optional<Character> c2) {
        if (c1.isEmpty()) return Optional.empty();
        if (c2.isEmpty()) return Optional.ofNullable(new Invalid());
        if (c1.get().equals(c2.get())) return Optional.empty();
        return Optional.ofNullable(new Before(c1.get(), c2.get()));
    }

    private Optional<Character> charAtOrNull(String s, int i) {
        if (i < 0 || i >= s.length()) return Optional.empty();
        return Optional.ofNullable(s.charAt(i));
    }

    private Optional<Info> wordInfo(String w1, String w2) {
        Optional<Info> res = Optional.empty();
        int i = 0;
        do {
            var c1 = charAtOrNull(w1, i);
            var c2 = charAtOrNull(w2, i);

            res = charInfo(c1, c2);
            i++;
        } while (res.isEmpty() && i < Math.max(w1.length(), w2.length()));

        return res;
    }

    private Set<Character> alphabet(Set<Character> acc, String word) {
        for (char c : word.toCharArray()) {
            acc.add(c);
        }

        return acc;
    }

    private String toAlphabetString(Set<Character> alphabet) {
        StringBuilder sb = new StringBuilder(alphabet.size());
        for (char c : alphabet) sb.append(c);
        String s = sb.toString();

        return s;
    }




    // w[i]   = [a1, a2, a3, a4, ..., aN]
    // w[i+1] = [b1, b2, b3, ..., bM]

    // a[i] == b[i] -> i++
    // a[i] <> b[i] -> a -> [b]
    // null vs b[i] next pair

    // b < c
    // a < c
    // c < b
    // ab, ac, c, b

    // ab, ad, bc, bd, d, a
    // b < d
    // a < b
    // c < d
    // b < d
    // d < a

    // ab, cd, x, y, a
    // a < c
    // c < x
    // x < y
    // y < a
    
}
