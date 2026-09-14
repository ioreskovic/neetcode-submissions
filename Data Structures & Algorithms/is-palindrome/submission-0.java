class Solution {
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            char left = s.charAt(l);
            if (!Character.isLetterOrDigit(left)) {
                l++;
                continue;
            }

            char right = s.charAt(r);
            if (!Character.isLetterOrDigit(right)) {
                r--;
                continue;
            }

            if (Character.toLowerCase(left) == Character.toLowerCase(right)) {
                l++;
                r--;
                continue;
            }

            return false;
        }

        return true;
    }
}
