class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int[] stack = new int[m * n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }

                int top = 0;
                long sum = 0;

                stack[top++] = r * n + c;
                sum += grid[r][c];
                grid[r][c] = 0;

                while (top > 0) {
                    int cell = stack[--top];
                    int x = cell / n;
                    int y = cell % n;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dr[d];
                        int ny = y + dc[d];

                        if (nx >= 0 && nx < m &&
                            ny >= 0 && ny < n &&
                            grid[nx][ny] > 0) {

                            sum += grid[nx][ny];
                            grid[nx][ny] = 0;
                            stack[top++] = nx * n + ny;
                        }
                    }
                }

                if (sum % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}