class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);

        for (int m = 63; m >= 1; m--) {
            long left = 2;
            long right = (long) Math.pow(num, 1.0 / m) + 1;

            while (left <= right) {
                long base = left + (right - left) / 2;
                int result = compare(num, base, m);

                if (result == 0) {
                    return String.valueOf(base);
                }

                if (result < 0) {
                    left = base + 1;
                } else {
                    right = base - 1;
                }
            }
        }

        return String.valueOf(num - 1);
    }

    private int compare(long n, long base, int m) {
        long sum = 1;

        for (int i = 0; i < m; i++) {
            if (sum > (n - 1) / base) {
                return 1;
            }

            sum = sum * base + 1;

            if (sum > n) {
                return 1;
            }
        }

        return Long.compare(sum, n);
    }
}