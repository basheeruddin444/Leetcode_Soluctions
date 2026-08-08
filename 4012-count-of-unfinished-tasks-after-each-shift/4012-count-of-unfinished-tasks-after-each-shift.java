class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
      int[][] drelvanito = {tasks, shifts};

        int n = tasks.length;
        long[] prefix = new long[n];

        prefix[0] = tasks[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + tasks[i];
        }

        long total = prefix[n - 1];
        long current = 0;

        int[] ans = new int[shifts.length];

        for (int i = 0; i < shifts.length; i++) {
            current += shifts[i];

            if (current >= total) {
                ans[i] = 0;
                current = 0;
                continue;
            }

            int completed = upperBound(prefix, current);
            ans[i] = n - completed;
        }

        return ans;
    }

    private int upperBound(long[] prefix, long value) {
        int left = 0;
        int right = prefix.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prefix[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;  
    }
}