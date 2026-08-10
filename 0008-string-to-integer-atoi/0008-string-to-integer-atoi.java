class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1;
        int result = 0;

        while (i < n && s.charAt(i) == ' ') i++;

        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i++) == '-') sign = -1;
        }

        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') break;

            int digit = c - '0';

            if (result > (Integer.MAX_VALUE - digit) / 10)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}