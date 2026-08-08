class Solution {
    public long maxPairStrength(int[] nums) {
        long answer = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long gcd = findGcd(nums[i], nums[j]);

                long strength = (long) nums[i] * nums[j] / (gcd * gcd);

                answer = Math.max(answer, strength);
            }
        }

        return answer;
    }

    private long findGcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}