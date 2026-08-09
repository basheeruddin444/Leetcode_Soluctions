class Solution {
    public int longestCommonSubpath(int n, int[][] paths) {
        int low = 0;
        int high = Integer.MAX_VALUE;

        for (int[] path : paths) {
            high = Math.min(high, path.length);
        }

        long mod1 = 1_000_000_007L;
        long mod2 = 1_000_000_009L;
        long base = 1_000_003L;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (exists(paths, mid, mod1, mod2, base)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    private boolean exists(int[][] paths, int len,
                           long mod1, long mod2, long base) {

        Set<Long> common = null;

        for (int[] path : paths) {
            if (path.length < len) {
                return false;
            }

            long[] hash1 = new long[path.length + 1];
            long[] hash2 = new long[path.length + 1];
            long[] power1 = new long[len + 1];
            long[] power2 = new long[len + 1];

            power1[0] = 1;
            power2[0] = 1;

            for (int i = 1; i <= len; i++) {
                power1[i] = power1[i - 1] * base % mod1;
                power2[i] = power2[i - 1] * base % mod2;
            }

            for (int i = 0; i < path.length; i++) {
                hash1[i + 1] = (hash1[i] * base + path[i] + 1) % mod1;
                hash2[i + 1] = (hash2[i] * base + path[i] + 1) % mod2;
            }

            Set<Long> current = new HashSet<>();

            for (int i = 0; i + len <= path.length; i++) {
                long h1 = (hash1[i + len]
                        - hash1[i] * power1[len] % mod1 + mod1) % mod1;

                long h2 = (hash2[i + len]
                        - hash2[i] * power2[len] % mod2 + mod2) % mod2;

                long key = (h1 << 32) ^ h2;
                current.add(key);
            }

            if (common == null) {
                common = current;
            } else {
                common.retainAll(current);

                if (common.isEmpty()) {
                    return false;
                }
            }
        }

        return common != null && !common.isEmpty();
    }
}