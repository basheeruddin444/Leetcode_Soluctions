class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        for (int i = n - 1; i >= 0; i--) {
            int[] cnt = new int[26];

            for (char c : s.toCharArray()) {
                cnt[c - 'a']++;
            }

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (cnt[x] == 0) {
                    possible = false;
                    break;
                }

                cnt[x]--;
            }

            if (!possible) {
                continue;
            }

            int x = target.charAt(i) - 'a';

            for (int c = x + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    StringBuilder ans = new StringBuilder(target.substring(0, i));
                    ans.append((char) ('a' + c));
                    cnt[c]--;

                    for (int k = 0; k < 26; k++) {
                        while (cnt[k] > 0) {
                            ans.append((char) ('a' + k));
                            cnt[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}