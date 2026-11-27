class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] frees = new int[n];
        int minPrice = Integer.MAX_VALUE;

        // Precompute free bonus copies for buying the first copy of each item
        // and track the overall minimum item price
        for (int i = 0; i < n; i++) {
            int factorI = items[i][0];
            int priceI = items[i][1];
            minPrice = Math.min(minPrice, priceI);

            int bonus = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factorI == 0) {
                    bonus++;
                }
            }
            frees[i] = bonus;
        }

        // Phase 1: 0/1 Knapsack where picking item i once gives 1 (bought) + frees[i] (free)
        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            int gain = 1 + frees[i];
            for (int b = budget; b >= cost; b--) {
                dp[b] = Math.max(dp[b], dp[b - cost] + gain);
            }
        }

        // Phase 2: Spend remaining budget (budget - b) greedily on the cheapest item
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            int extra = (budget - b) / minPrice;
            ans = Math.max(ans, dp[b] + extra);
        }

        return ans;
    }
}
