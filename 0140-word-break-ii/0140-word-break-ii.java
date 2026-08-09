class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, set, memo);
    }

    private List<String> dfs(String s, int i, Set<String> set,
                             Map<Integer, List<String>> memo) {
        if (i == s.length()) {
            return new ArrayList<>(List.of(""));
        }

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        List<String> ans = new ArrayList<>();

        for (int j = i + 1; j <= s.length(); j++) {
            String word = s.substring(i, j);

            if (!set.contains(word)) {
                continue;
            }

            for (String rest : dfs(s, j, set, memo)) {
                ans.add(rest.isEmpty() ? word : word + " " + rest);
            }
        }

        memo.put(i, ans);
        return ans;
    }
}