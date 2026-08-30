class Solution {
    public int minimumDeletions(int[] nums) {

        int min = nums[0];
        int max = nums[0];

        int minindex = 0;
        int maxindex = 0;

        // Find min, max and their indexes
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] < min) {
                min = nums[i];
                minindex = i;
            }

            if (nums[i] > max) {
                max = nums[i];
                maxindex = i;
            }
        }

        int n = nums.length;

        // Number of deletions needed from front
        int frontmin = minindex + 1;
        int frontmax = maxindex + 1;

        // Number of deletions needed from back
        int backmin = n - minindex;
        int backmax = n - maxindex;

        // 1. Both from front
        int bothFront = Math.max(frontmin, frontmax);

        // 2. Both from back
        int bothBack = Math.max(backmin, backmax);

        // 3. Min from front, max from back
        int minFrontMaxBack = frontmin + backmax;

        // 4. Min from back, max from front
        int minBackMaxFront = backmin + frontmax;

        return Math.min(
            Math.min(bothFront, bothBack),
            Math.min(minFrontMaxBack, minBackMaxFront)
        );
    }
}