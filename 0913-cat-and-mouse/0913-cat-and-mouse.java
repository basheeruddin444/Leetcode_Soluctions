class Solution {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][0] = graph[m].length;
                int count = 0;

                for (int next : graph[c]) {
                    if (next != 0) {
                        count++;
                    }
                }

                degree[m][c][1] = count;
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for (int c = 1; c < n; c++) {
            color[0][c][0] = 1;
            color[0][c][1] = 1;
            queue.offer(new int[]{0, c, 0});
            queue.offer(new int[]{0, c, 1});
        }

        for (int i = 1; i < n; i++) {
            color[i][i][0] = 2;
            color[i][i][1] = 2;
            queue.offer(new int[]{i, i, 0});
            queue.offer(new int[]{i, i, 1});
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();

            int mouse = state[0];
            int cat = state[1];
            int turn = state[2];
            int result = color[mouse][cat][turn];

            if (turn == 0) {
                for (int prevCat : graph[cat]) {
                    if (prevCat == 0 || color[mouse][prevCat][1] != 0) {
                        continue;
                    }

                    if (result == 2) {
                        color[mouse][prevCat][1] = 2;
                        queue.offer(new int[]{mouse, prevCat, 1});
                    } else {
                        degree[mouse][prevCat][1]--;

                        if (degree[mouse][prevCat][1] == 0) {
                            color[mouse][prevCat][1] = 1;
                            queue.offer(new int[]{mouse, prevCat, 1});
                        }
                    }
                }
            } else {
                for (int prevMouse : graph[mouse]) {
                    if (color[prevMouse][cat][0] != 0) {
                        continue;
                    }

                    if (result == 1) {
                        color[prevMouse][cat][0] = 1;
                        queue.offer(new int[]{prevMouse, cat, 0});
                    } else {
                        degree[prevMouse][cat][0]--;

                        if (degree[prevMouse][cat][0] == 0) {
                            color[prevMouse][cat][0] = 2;
                            queue.offer(new int[]{prevMouse, cat, 0});
                        }
                    }
                }
            }
        }

        return color[1][2][0];
    }
}