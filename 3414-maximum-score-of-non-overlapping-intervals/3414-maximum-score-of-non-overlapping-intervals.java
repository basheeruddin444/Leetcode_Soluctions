import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) return Integer.compare(x[0], y[0]);
            if (x[1] != y[1]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[3], y[3]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n;
            while (l < r) {
                int m = (l + r) >>> 1;
                if (a[m][0] > a[i][1]) r = m;
                else l = m + 1;
            }
            next[i] = l;
        }

        Result[][] dp = new Result[n + 1][5];

        for (int i = 0; i <= n; i++)
            dp[i][0] = new Result(0, new ArrayList<>());

        for (int k = 0; k <= 4; k++)
            dp[n][k] = new Result(0, new ArrayList<>());

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                Result skip = dp[i + 1][k];
                Result after = dp[next[i]][k - 1];

                List<Integer> list = new ArrayList<>(after.indices);
                list.add(a[i][3]);
                Collections.sort(list);

                Result take = new Result(a[i][2] + after.score, list);
                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].indices.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private Result better(Result a, Result b) {
        if (a.score != b.score)
            return a.score > b.score ? a : b;

        int n = Math.min(a.indices.size(), b.indices.size());

        for (int i = 0; i < n; i++) {
            if (!a.indices.get(i).equals(b.indices.get(i)))
                return a.indices.get(i) < b.indices.get(i) ? a : b;
        }

        return a.indices.size() < b.indices.size() ? a : b;
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }
}