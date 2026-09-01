import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0, k = 0;
        int[][] litter = new int[10][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litter[k][0] = i;
                    litter[k][1] = j;
                    k++;
                }
            }
        }

        int full = (1 << k) - 1;

        if (k == 0) return 0;

        boolean[][][][] visited =
            new boolean[m][n][1 << k][energy + 1];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, 0, energy, 0});
        visited[sr][sc][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int e = cur[3];
            int moves = cur[4];

            if (mask == full) return moves;

            if (e == 0 && classroom[r].charAt(c) != 'R')
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                if (classroom[nr].charAt(nc) == 'X')
                    continue;

                int ne = e - 1;

                if (classroom[nr].charAt(nc) == 'R')
                    ne = energy;

                int nmask = mask;

                for (int i = 0; i < k; i++) {
                    if (litter[i][0] == nr && litter[i][1] == nc) {
                        nmask |= (1 << i);
                        break;
                    }
                }

                if (!visited[nr][nc][nmask][ne]) {
                    visited[nr][nc][nmask][ne] = true;
                    q.offer(new int[]{nr, nc, nmask, ne, moves + 1});
                }
            }
        }

        return -1;
    }
}