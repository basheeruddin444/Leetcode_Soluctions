class Solution {
    public String longestDupSubstring(String s) {
        int n = s.length();
        int low = 1, high = n - 1;
        int start = -1;
        int length = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int pos = find(s, mid);

            if (pos != -1) {
                start = pos;
                length = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return start == -1 ? "" : s.substring(start, start + length);
    }

    private int find(String s, int len) {
        long mod1 = 1_000_000_007L;
        long mod2 = 1_000_000_009L;
        long base = 911382323L;

        int n = s.length();

        long h1 = 0;
        long h2 = 0;
        long p1 = 1;
        long p2 = 1;

        for (int i = 0; i < len; i++) {
            h1 = (h1 * base + s.charAt(i)) % mod1;
            h2 = (h2 * base + s.charAt(i)) % mod2;
            p1 = p1 * base % mod1;
            p2 = p2 * base % mod2;
        }

        Set<Long> seen = new HashSet<>();
        seen.add((h1 << 32) ^ h2);

        for (int i = len; i < n; i++) {
            h1 = (h1 * base + s.charAt(i)
                    - s.charAt(i - len) * p1) % mod1;

            h2 = (h2 * base + s.charAt(i)
                    - s.charAt(i - len) * p2) % mod2;

            if (h1 < 0) h1 += mod1;
            if (h2 < 0) h2 += mod2;

            long key = (h1 << 32) ^ h2;

            if (!seen.add(key)) {
                return i - len + 1;
            }
        }

        return -1;
    }
}