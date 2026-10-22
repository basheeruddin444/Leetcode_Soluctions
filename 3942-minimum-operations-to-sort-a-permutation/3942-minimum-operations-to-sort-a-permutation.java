class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // Case 1: Rotate nums to obtain sorted order
        int k = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                k = i;
                break;
            }
        }

        boolean valid = true;

        for (int i = 0; i < n; i++) {
            if (nums[(i + k) % n] != i) {
                valid = false;
                break;
            }
        }

        if (valid) {
            ans = Math.min(ans, Math.min(k, n - k + 2));
        }

        // Case 2: Reverse nums, then rotate
        int[] rev = new int[n];

        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }

        k = -1;

        for (int i = 0; i < n; i++) {
            if (rev[i] == 0) {
                k = i;
                break;
            }
        }

        valid = true;

        for (int i = 0; i < n; i++) {
            if (rev[(i + k) % n] != i) {
                valid = false;
                break;
            }
        }

        if (valid) {
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}