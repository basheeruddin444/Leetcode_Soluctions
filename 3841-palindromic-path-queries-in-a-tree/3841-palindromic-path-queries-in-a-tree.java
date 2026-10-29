
import java.util.*;

class Solution {
    private int LOG;
    private List<Integer>[] graph;
    private int[] tin, tout, depth, bit, chars, prefix;
    private int[][] up;
    private int timer;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        bit = new int[n + 2];
        chars = new int[n];
        prefix = new int[n];
        up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            chars[i] = 1 << (s.charAt(i) - 'a');
        }

        buildTree(n);

        // Fenwick Tree starts empty.
        // It stores only changes to root-prefix masks.

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                int newMask = 1 << (p[2].charAt(0) - 'a');
                int delta = chars[u] ^ newMask;

                if (delta != 0) {
                    rangeXor(tin[u], tout[u], delta);
                    chars[u] = newMask;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int ancestor = lca(u, v);

                int mask = getPrefix(u)
                         ^ getPrefix(v)
                         ^ chars[ancestor];

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void buildTree(int n) {
        timer = 0;

        // Each frame stores: node, parent, next-neighbor index.
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0, 0});

        tin[0] = ++timer;
        prefix[0] = chars[0];
        up[0][0] = 0;

        for (int k = 1; k < LOG; k++) {
            up[k][0] = 0;
        }

        while (!stack.isEmpty()) {
            int[] frame = stack.peek();
            int u = frame[0];
            int parent = frame[1];

            if (frame[2] < graph[u].size()) {
                int v = graph[u].get(frame[2]++);
                if (v == parent) {
                    continue;
                }

                depth[v] = depth[u] + 1;
                prefix[v] = prefix[u] ^ chars[v];
                up[0][v] = u;

                for (int k = 1; k < LOG; k++) {
                    up[k][v] = up[k - 1][up[k - 1][v]];
                }

                tin[v] = ++timer;
                stack.push(new int[]{v, u, 0});
            } else {
                tout[u] = timer;
                stack.pop();
            }
        }
    }

    private void rangeXor(int l, int r, int value) {
        add(l, value);
        add(r + 1, value);
    }

    private void add(int i, int value) {
        while (i < bit.length) {
            bit[i] ^= value;
            i += i & -i;
        }
    }

    private int sum(int i) {
        int result = 0;

        while (i > 0) {
            result ^= bit[i];
            i -= i & -i;
        }

        return result;
    }

    private int getPrefix(int u) {
        return prefix[u] ^ sum(tin[u]);
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }
}
