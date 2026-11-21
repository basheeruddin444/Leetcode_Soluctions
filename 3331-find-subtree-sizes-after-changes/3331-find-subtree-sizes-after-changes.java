
import java.util.*;

class Solution {

    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] last;
    int[] ans;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;
        this.s = s;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        // Construct modified tree
        dfs(0, -1, newTree);

        // Calculate subtree sizes
        ans = new int[n];
        calculateSize(0);

        return ans;
    }

    void dfs(int node, int originalParent,
             List<Integer>[] newTree) {

        int ch = s.charAt(node) - 'a';

        // Find closest matching ancestor
        int newParent = last[ch];

        // Save previous ancestor
        int previous = last[ch];

        // Update closest ancestor
        last[ch] = node;

        // Add node to modified tree
        if (node != 0) {
            if (newParent == -1) {
                newTree[originalParent].add(node);
            } else {
                newTree[newParent].add(node);
            }
        }

        // Traverse original children
        for (int child : tree[node]) {
            dfs(child, node, newTree);
        }

        // Restore previous ancestor
        last[ch] = previous;
    }

    int calculateSize(int node) {

        int size = 1;

        for (int child : newTree[node]) {
            size += calculateSize(child);
        }

        ans[node] = size;

        return size;
    }
}
