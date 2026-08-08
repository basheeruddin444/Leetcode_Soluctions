import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] right = new int[m];
        Arrays.fill(right, -1);

        int j = m - 1;

        for (int i = n - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                right[j] = i;
                j--;
            }
        }

        int[] answer = new int[m];

        int p = 0;
        boolean usedChange = false;

        for (int i = 0; i < n && p < m; i++) {
            if (word1.charAt(i) == word2.charAt(p)) {
                answer[p] = i;
                p++;
            } else if (!usedChange) {
                if (p == m - 1 || right[p + 1] > i) {
                    answer[p] = i;
                    p++;
                    usedChange = true;
                }
            }
        }

        if (p == m) {
            return answer;
        }

        return new int[0];
    }
}