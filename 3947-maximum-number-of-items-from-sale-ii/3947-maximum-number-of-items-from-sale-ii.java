
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        // Step 1: Find maximum factor and minimum price
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Count frequency of factors
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Step 3: Count divisible items
        int[] freeCount = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int j = f; j <= maxFactor; j += f) {
                freeCount[f] += freq[j];
            }

            if (freeCount[f] > 0) {
                freeCount[f]--;
            }
        }

        // Step 4: Sort by increasing price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long answer = 0;

        // Step 5: Buy bonus copies first
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if (2L * price >= 2L * minPrice * 2L) {
                break;
            }

            long take = Math.min(
                (long) freeCount[factor],
                remaining / price
            );

            answer += 2L * take;
            remaining -= take * price;
        }

        // Step 6: Buy normal copies
        answer += remaining / minPrice;

        return (int) answer;
    }
}
