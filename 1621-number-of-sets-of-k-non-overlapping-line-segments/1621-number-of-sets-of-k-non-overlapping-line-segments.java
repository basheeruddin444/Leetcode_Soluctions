class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007L;

        long[] dp = new long[k + 1];
        long[] end = new long[k + 1];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = Math.min(k, i); j >= 1; j--) {
                end[j] = (end[j] + dp[j - 1]) % MOD;
                dp[j] = (dp[j] + end[j]) % MOD;
            }
        }

        return (int) dp[k];
    }
}