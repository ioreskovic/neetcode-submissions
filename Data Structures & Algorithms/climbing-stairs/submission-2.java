class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public Solution() {
        this.memo.put(0, 1);
        this.memo.put(1, 1);
    }

    public int climbStairs(int n) {
        if (!memo.containsKey(n)) {
            memo.put(n, climbStairs(n - 1) + climbStairs(n - 2));
        }
        return memo.get(n);
    }
}
