
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int[] count = new int[100001];
        long total = 0;

        for (int i = 0; i < nums1.length; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            count[d]++;
            total += d;
        }

        if (total <= k) return 0;

        for (int d = 100000; d > 0 && k > 0; d--) {
            if (count[d] == 0) continue;

            long move = Math.min(k, count[d]);
            count[d] -= (int) move;
            count[d - 1] += (int) move;
            k -= move;

            if (move < count[d] + move) {
                d++;
            }
        }

        long answer = 0;

        for (int d = 1; d <= 100000; d++) {
            answer += (long) d * d * count[d];
        }

        return answer;
    }
}
