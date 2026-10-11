class Solution {
    static List<Integer> digits(int n) {
        List<Integer> result = new ArrayList<>();

        while (n > 0) {
            int digit = n % 10;
            result.add(digit);
            n = n / 10;
        }

        return result;
    }

    static int transform(int n) {
        int x = 0;
        for (int d : digits(n)) {
            x = x + d * d;
        }
        return x;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();

        while(!seen.contains(n)) {
            seen.add(n);
            if (n == 1) return true;
            n = transform(n);
        }

        return false;
    }
}
