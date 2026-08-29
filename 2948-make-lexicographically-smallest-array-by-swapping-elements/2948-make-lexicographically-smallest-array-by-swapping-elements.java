import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = ((long) nums[i] << 32) | (i & 0xffffffffL);
        }

        Arrays.sort(arr);

        int start = 0;

        while (start < n) {
            int end = start;

            while (end + 1 < n) {
                long value1 = arr[end] >>> 32;
                long value2 = arr[end + 1] >>> 32;

                if (value2 - value1 > limit) {
                    break;
                }

                end++;
            }

            int size = end - start + 1;
            int[] indices = new int[size];

            for (int i = 0; i < size; i++) {
                indices[i] = (int) arr[start + i];
            }

            Arrays.sort(indices);

            for (int i = 0; i < size; i++) {
                nums[indices[i]] = (int) (arr[start + i] >>> 32);
            }

            start = end + 1;
        }

        return nums;
    }
}