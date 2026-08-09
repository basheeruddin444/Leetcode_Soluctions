class Solution {
    public int countDigitOne(int n) {
        long factor = 1;
        int answer = 0;

        while (factor <= n) {
            long lower = n % factor;
            long current = (n / factor) % 10;
            long higher = n / (factor * 10);

            if (current == 0) {
                answer += higher * factor;
            } else if (current == 1) {
                answer += higher * factor + lower + 1;
            } else {
                answer += (higher + 1) * factor;
            }

            factor *= 10;
        }

        return answer;
    }
}