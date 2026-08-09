class Solution {
    public int minimumCost(String target, String[] words, int[] costs) {
        int n = target.length();
        long mod = 1_000_000_007L;
        long base = 911382323L;

        long[] hash = new long[n + 1];
        long[] power = new long[n + 1];

        power[0] = 1;

        for (int i = 0; i < n; i++) {
            hash[i + 1] = (hash[i] * base + target.charAt(i)) % mod;
            power[i + 1] = power[i] * base % mod;
        }

        Map<Long, Integer> minCost = new HashMap<>();
        Set<Integer> lengths = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            long h = 0;

            for (int j = 0; j < words[i].length(); j++) {
                h = (h * base + words[i].charAt(j)) % mod;
            }

            minCost.merge(h, costs[i], Math::min);
            lengths.add(words[i].length());
        }

        List<Integer> lens = new ArrayList<>(lengths);
        Collections.sort(lens);

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int len : lens) {
                if (len > i) {
                    break;
                }

                if (dp[i - len] == Integer.MAX_VALUE) {
                    continue;
                }

                long h = (hash[i] - hash[i - len] * power[len] % mod + mod) % mod;

                if (minCost.containsKey(h)) {
                    dp[i] = Math.min(dp[i], dp[i - len] + minCost.get(h));
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}