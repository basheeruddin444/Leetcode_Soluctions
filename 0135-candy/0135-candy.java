class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int result = n;
        int i = 0;

        while (i < n - 1) {

            if (ratings[i] == ratings[i + 1]) {
                i++;
                continue;
            }

            int up = 0;
            int down = 0;

            while (i < n - 1 && ratings[i] < ratings[i + 1]) {
                up++;
                i++;
            }

            while (i < n - 1 && ratings[i] > ratings[i + 1]) {
                down++;
                i++;
            }

            result += (up * (up + 1)) / 2;
            result += (down * (down + 1)) / 2;
            result -= Math.min(up, down);
        }

        return result;
    }
}