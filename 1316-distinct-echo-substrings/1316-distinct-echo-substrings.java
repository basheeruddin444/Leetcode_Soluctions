class Solution {
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        long mod = 1_000_000_007L;
        long base = 31;

        long[] hash = new long[n + 1];
        long[] power = new long[n + 1];

        power[0] = 1;

        for (int i = 0; i < n; i++) {
            hash[i + 1] = (hash[i] * base + text.charAt(i)) % mod;
            power[i + 1] = power[i] * base % mod;
        }

        Set<Long> seen = new HashSet<>();

        for (int len = 1; len * 2 <= n; len++) {
            for (int start = 0; start + 2 * len <= n; start++) {
                long first = getHash(hash, power, start, start + len, mod);
                long second = getHash(hash, power, start + len, start + 2 * len, mod);

                if (first == second) {
                    seen.add(first);
                }
            }
        }

        return seen.size();
    }

    private long getHash(long[] hash, long[] power,
                          int left, int right, long mod) {
        return (hash[right] - hash[left] * power[right - left] % mod + mod) % mod;
    }
}