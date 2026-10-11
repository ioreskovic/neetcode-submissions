class Solution {
    public int[] plusOne(int[] digits) {
        int size = digits.length;

        int[] result = new int[size + 1];

        int carry = 1;
        for (int i = size - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            result[i + 1] = sum % 10;
            carry = sum / 10;
        }

        if (carry == 1) {
            result[0] = 1;
            return result;
        } else {
            return Arrays.copyOfRange(result, 1, size + 1);
        }
    }
}
