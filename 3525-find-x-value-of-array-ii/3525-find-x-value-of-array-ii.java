class Solution {
    int n, k;
    long[][] pref;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        pref = new long[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val % k);

            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = (int) res.pref[x];
        }

        return ans;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            prod[node] = nums[l] % k;
            pref[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) >>> 1;

        build(node << 1, l, mid, nums);
        build(node << 1 | 1, mid + 1, r, nums);

        merge(node, node << 1, node << 1 | 1);
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            prod[node] = val;
            for (int i = 0; i < k; i++)
                pref[node][i] = 0;

            pref[node][val] = 1;
            return;
        }

        int mid = (l + r) >>> 1;

        if (idx <= mid)
            update(node << 1, l, mid, idx, val);
        else
            update(node << 1 | 1, mid + 1, r, idx, val);

        merge(node, node << 1, node << 1 | 1);
    }

    void merge(int node, int left, int right) {
        prod[node] = (prod[left] * prod[right]) % k;

        for (int i = 0; i < k; i++)
            pref[node][i] = pref[left][i];

        for (int r = 0; r < k; r++) {
            if (pref[right][r] == 0)
                continue;

            int x = (prod[left] * r) % k;
            pref[node][x] += pref[right][r];
        }
    }

    Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            Node res = new Node(k);
            res.prod = prod[node];

            for (int i = 0; i < k; i++)
                res.pref[i] = pref[node][i];

            return res;
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid)
            return query(node << 1, l, mid, ql, qr);

        if (ql > mid)
            return query(node << 1 | 1, mid + 1, r, ql, qr);

        Node left = query(node << 1, l, mid, ql, qr);
        Node right = query(node << 1 | 1, mid + 1, r, ql, qr);

        return combine(left, right);
    }

    Node combine(Node left, Node right) {
        Node res = new Node(k);

        res.prod = (left.prod * right.prod) % k;

        for (int i = 0; i < k; i++)
            res.pref[i] = left.pref[i];

        for (int r = 0; r < k; r++) {
            if (right.pref[r] == 0)
                continue;

            int x = (left.prod * r) % k;
            res.pref[x] += right.pref[r];
        }

        return res;
    }

    static class Node {
        int prod;
        long[] pref;

        Node(int k) {
            pref = new long[k];
        }
    }
}