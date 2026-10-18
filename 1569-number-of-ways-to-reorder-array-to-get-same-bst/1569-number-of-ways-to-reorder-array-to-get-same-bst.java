class Solution {
    static final long MOD = 1_000_000_007;
    long[][] comb = new long[1001][1001];

    public int numOfWays(int[] nums) {
        for (int i = 0; i <= 1000; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        long ans = dfs(nums);
        return (int) ((ans - 1 + MOD) % MOD);
    }

    private long dfs(int[] nums) {
        if (nums.length <= 2) {
            return 1;
        }

        int root = nums[0];
        int leftCount = 0;
        int rightCount = 0;

        for (int num : nums) {
            if (num < root) leftCount++;
            else if (num > root) rightCount++;
        }

        int[] left = new int[leftCount];
        int[] right = new int[rightCount];

        int l = 0, r = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < root) left[l++] = nums[i];
            else right[r++] = nums[i];
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        long ways = comb[leftCount + rightCount][leftCount];
        ways = ways * leftWays % MOD;
        ways = ways * rightWays % MOD;

        return ways;
    }
}