
import java.util.*;

class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
        }

        // dist[node][count]
        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // PriorityQueue: {cost, node, count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Starting node
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            long cost = current[0];
            int node = (int) current[1];
            int count = (int) current[2];

            // Ignore outdated states
            if (cost != dist[node][count]) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return (int) cost;
            }

            // Explore neighbors
            for (int[] next : graph[node]) {

                int neighbor = next[0];
                int weight = next[1];

                int newCount;

                if (labels.charAt(node) == labels.charAt(neighbor)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Invalid consecutive count
                if (newCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                if (newCost < dist[neighbor][newCount]) {

                    dist[neighbor][newCount] = newCost;

                    pq.offer(new long[]{
                        newCost,
                        neighbor,
                        newCount
                    });
                }
            }
        }

        return -1;
    }
}
