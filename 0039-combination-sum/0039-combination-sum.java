class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        solve(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void solve(int[] a, int target, int start,
                       List<Integer> cur, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i < a.length && a[i] <= target; i++) {
            cur.add(a[i]);
            solve(a, target - a[i], i, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}