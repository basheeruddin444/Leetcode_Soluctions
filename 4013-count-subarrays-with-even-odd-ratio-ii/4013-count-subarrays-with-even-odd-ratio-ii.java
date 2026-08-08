 
class Solution {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                prefix[i + 1] = prefix[i] + b;
            } else {
                prefix[i + 1] = prefix[i] - a;
            }
        }

        long[] values = prefix.clone();
        java.util.Arrays.sort(values);

        int size = 0;

        for (int i = 0; i <= n; i++) {
            if (i == 0 || values[i] != values[i - 1]) {
                values[size++] = values[i];
            }
        }

        FenwickTree tree = new FenwickTree(size);

        long answer = 0;
        int added = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) {
                while (added <= i) {
                    int pos = lowerBound(values, size, prefix[added]);
                    tree.add(pos + 1, 1);
                    added++;
                }
            }

            int pos = lowerBound(values, size, prefix[i + 1]);

            answer += tree.sum(size) - tree.sum(pos);
        }

        return answer;
    }

    private int lowerBound(long[] values, int size, long target) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (values[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static class FenwickTree {
        int[] tree;

        FenwickTree(int n) {
            tree = new int[n + 1];
        }

        void add(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        int sum(int index) {
            int result = 0;

            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }
    }
}