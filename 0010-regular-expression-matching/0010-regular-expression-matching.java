class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;

        for (int j = 2; j <= m; j += 2) {
            if (p.charAt(j - 1) == '*')
                dp[j] = dp[j - 2];
        }

        for (int i = 1; i <= n; i++) {
            boolean prev = dp[0];
            dp[0] = false;

            for (int j = 1; j <= m; j++) {
                boolean old = dp[j];

                if (p.charAt(j - 1) == '*') {
                    dp[j] = dp[j - 2];

                    char c = p.charAt(j - 2);

                    if (c == '.' || c == s.charAt(i - 1))
                        dp[j] |= old;
                } else {
                    dp[j] = prev &&
                            (p.charAt(j - 1) == '.' ||
                             p.charAt(j - 1) == s.charAt(i - 1));
                }

                prev = old;
            }
        }

        return dp[m];
    }
}