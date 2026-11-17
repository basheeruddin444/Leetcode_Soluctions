
class Solution {

    int[][] cost;
    int[][] dp;

    public int minimumChanges(String s, int k) {

        int n = s.length();

        cost = new int[n][n];

        // Step 1: Precompute minimum changes
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;

                cost[i][j] = getCost(s, i, j);
            }
        }

        // Step 2: DP initialization
        dp = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            java.util.Arrays.fill(dp[i], 1000000);
        }

        dp[0][0] = 0;

        // Step 3: Divide into exactly k substrings
        for (int parts = 1; parts <= k; parts++) {

            for (int end = 2 * parts; end <= n; end++) {

                for (int start = 2 * (parts - 1);
                     start <= end - 2; start++) {

                    dp[parts][end] = Math.min(
                        dp[parts][end],
                        dp[parts - 1][start] +
                        cost[start][end - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }

    // Minimum changes to make s[l...r] semi-palindrome
    private int getCost(String s, int l, int r) {

        int len = r - l + 1;

        int ans = Integer.MAX_VALUE;

        // Find proper divisors
        for (int d = 1; d < len; d++) {

            if (len % d != 0) {
                continue;
            }

            int changes = 0;

            // Compare symmetric characters in each group
            for (int offset = 0; offset < d; offset++) {

                int left = l + offset;
                int right = r - (d - 1 - offset);

                while (left < right) {

                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }

                    left += d;
                    right -= d;
                }
            }

            ans = Math.min(ans, changes);
        }

        return ans;
    }
}
