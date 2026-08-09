class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }

        long upper = (long) Math.pow(10, n) - 1;
        long lower = (long) Math.pow(10, n - 1);

        for (long left = upper; left >= lower; left--) {
            String part = String.valueOf(left);
            long palindrome = Long.parseLong(
                part + new StringBuilder(part).reverse()
            );

            for (long factor = upper; factor * factor >= palindrome; factor--) {
                if (palindrome % factor == 0) {
                    long other = palindrome / factor;

                    if (other >= lower && other <= upper) {
                        return (int) (palindrome % 1337);
                    }
                }
            }
        }

        return 0;
    }
}