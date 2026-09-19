class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> acc = new ArrayList<>();
        comb(n, k, 1, new LinkedList<>(), acc);
        return acc;
    }

    private void comb(int n, int k, int i, LinkedList<Integer> curr, List<List<Integer>> acc) {
        if (curr.size() == k) {
            acc.add(List.copyOf(curr));
            return;
        }

        if (i > n) {
            return;
        }

        // take ith and continue
        curr.offerLast(i);
        comb(n, k, i + 1, curr, acc);

        // skip ith and continue
        curr.pollLast();
        comb(n, k, i + 1, curr, acc);
    }
}