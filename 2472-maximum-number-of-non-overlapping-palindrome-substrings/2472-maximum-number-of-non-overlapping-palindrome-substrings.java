class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            pal[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                pal[i][j] = s.charAt(i) == s.charAt(j) &&
                        (j - i == 1 || pal[i + 1][j - 1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int start = 0; start + k <= i; start++) {
                if (pal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}