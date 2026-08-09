class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = buildSuffixArray(nums);
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = buildLCP(nums, sa, rank);

        int ans = n;

        for (int r = 0; r < n; r++) {
            int pos = sa[r];
            int best = 0;

            if (r > 0) {
                best = Math.max(best, lcp[r - 1]);
            }

            if (r < n - 1) {
                best = Math.max(best, lcp[r]);
            }

            if (best < n - pos) {
                ans = Math.min(ans, best + 1);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        for (int len = 1; len < n; len *= 2) {
            Integer[] order = new Integer[n];

            for (int i = 0; i < n; i++) {
                order[i] = sa[i];
            }

            final int currentLen = len;

            Arrays.sort(order, (a, b) -> {
                if (rank[a] != rank[b]) {
                    return Integer.compare(rank[a], rank[b]);
                }

                int ra = a + currentLen < n ? rank[a + currentLen] : -1;
                int rb = b + currentLen < n ? rank[b + currentLen] : -1;

                return Integer.compare(ra, rb);
            });

            for (int i = 0; i < n; i++) {
                sa[i] = order[i];
            }

            temp[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                boolean different = rank[a] != rank[b];

                int aNext = a + len < n ? rank[a + len] : -1;
                int bNext = b + len < n ? rank[b + len] : -1;

                if (aNext != bNext) {
                    different = true;
                }

                temp[b] = temp[a] + (different ? 1 : 0);
            }

            for (int i = 0; i < n; i++) {
                rank[i] = temp[i];
            }

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

        return sa;
    }

    private int[] buildLCP(int[] nums, int[] sa, int[] rank) {
        int n = nums.length;
        int[] lcp = new int[n - 1];

        int common = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == n - 1) {
                common = 0;
                continue;
            }

            int j = sa[r + 1];

            while (i + common < n &&
                   j + common < n &&
                   nums[i + common] == nums[j + common]) {
                common++;
            }

            lcp[r] = common;

            if (common > 0) {
                common--;
            }
        }

        return lcp;
    }
}