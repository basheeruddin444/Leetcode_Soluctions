class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;

        if (n <= 2) {
            return n;
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int same = 1;
            int best = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    same++;
                    continue;
                }

                int gcd = gcd(Math.abs(dx), Math.abs(dy));

                dx /= gcd;
                dy /= gcd;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1;
                }

                String key = dx + "#" + dy;
                map.put(key, map.getOrDefault(key, 0) + 1);

                best = Math.max(best, map.get(key));
            }

            answer = Math.max(answer, best + same);
        }

        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}