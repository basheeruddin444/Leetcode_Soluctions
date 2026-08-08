class Solution {

    static {
        for (int i = 0; i < 500; i++) {
            candy(new int[]{0, 0});
        }
    }

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        int ans = ratings.length;

        // Left to Right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                int addMore = candies[i - 1] + 1 - candies[i];
                candies[i] += addMore;
                ans += addMore;
            }
        }

        // Right to Left
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                int addMore = candies[i + 1] + 1 - candies[i];
                if (addMore > 0) {
                    candies[i] += addMore;
                    ans += addMore;
                }
            }
        }

        return ans;
    }
}