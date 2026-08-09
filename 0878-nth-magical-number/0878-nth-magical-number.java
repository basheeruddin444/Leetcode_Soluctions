class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        long mod = 1_000_000_007L;
        long left = 1;
        long right = (long) n * Math.min(a, b);

        long lcm = (long) a / gcd(a, b) * b;

        while (left < right) {
            long mid = left + (right - left) / 2;

            long count = mid / a + mid / b - mid / lcm;

            if (count >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (int) (left % mod);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}