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
        int slow = n;
        int fast = transform(n);

        while (fast != 1 && slow != fast) {
            slow = transform(slow);
            fast = transform(transform(fast));
        }

        return fast == 1;
    }
}
