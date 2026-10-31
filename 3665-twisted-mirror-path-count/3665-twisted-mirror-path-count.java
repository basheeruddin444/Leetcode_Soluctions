
class Solution {
    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long MOD = 1_000_000_007L;

        // Ways entering a cell from the left
        long[][] right = new long[m][n];

        // Ways entering a cell from above
        long[][] down = new long[m][n];

        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == m - 1 && j == n - 1) {
                    return (int) ((right[i][j] + down[i][j]) % MOD);
                }

                if (grid[i][j] == 1) {

                    // Entered from left -> reflected downward
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + right[i][j]) % MOD;
                    }

                    // Entered from above -> reflected rightward
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + down[i][j]) % MOD;
                    }

                } else {

                    long total = (right[i][j] + down[i][j]) % MOD;

                    // Move right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + total) % MOD;
                    }

                    // Move down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + total) % MOD;
                    }
                }
            }
        }

        return 0;
    }
}
