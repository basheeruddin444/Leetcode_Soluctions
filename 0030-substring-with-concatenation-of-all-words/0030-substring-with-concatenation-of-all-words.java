class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        if (s.length() == 0 || words.length == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (totalLen > s.length()) {
            return ans;
        }

        Map<String, Integer> need = new HashMap<>();

        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }

        for (int start = 0; start < wordLen; start++) {
            int left = start;
            int count = 0;
            Map<String, Integer> have = new HashMap<>();

            for (int right = start; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (!need.containsKey(word)) {
                    have.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                have.put(word, have.getOrDefault(word, 0) + 1);
                count++;

                while (have.get(word) > need.get(word)) {
                    String remove = s.substring(left, left + wordLen);
                    have.put(remove, have.get(remove) - 1);
                    left += wordLen;
                    count--;
                }

                if (count == wordCount) {
                    ans.add(left);

                    String remove = s.substring(left, left + wordLen);
                    have.put(remove, have.get(remove) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return ans;
    }
}