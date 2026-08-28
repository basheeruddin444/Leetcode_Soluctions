class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1 || (n % 2 == 0 && odd != 0)) {
            return "";
        }

        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        String targetHalf = target.substring(0, half);

        String same = buildEqual(targetHalf, halfCnt);

        if (same != null) {
            String ans = make(same, mid);

            if (ans.compareTo(target) > 0) {
                return ans;
            }
        }

        for (int pos = half - 1; pos >= 0; pos--) {
            int[] temp = halfCnt.clone();
            boolean ok = true;

            for (int i = 0; i < pos; i++) {
                int x = targetHalf.charAt(i) - 'a';

                if (temp[x] == 0) {
                    ok = false;
                    break;
                }

                temp[x]--;
            }

            if (!ok) {
                continue;
            }

            int cur = targetHalf.charAt(pos) - 'a';

            for (int c = cur + 1; c < 26; c++) {
                if (temp[c] == 0) {
                    continue;
                }

                StringBuilder left = new StringBuilder();

                for (int i = 0; i < pos; i++) {
                    left.append(targetHalf.charAt(i));
                }

                left.append((char) ('a' + c));
                temp[c]--;

                for (int x = 0; x < 26; x++) {
                    while (temp[x] > 0) {
                        left.append((char) ('a' + x));
                        temp[x]--;
                    }
                }

                String ans = make(left.toString(), mid);

                if (ans.compareTo(target) > 0) {
                    return ans;
                }
            }
        }

        return "";
    }

    private String buildEqual(String s, int[] cnt) {
        int[] temp = cnt.clone();

        for (char c : s.toCharArray()) {
            int x = c - 'a';

            if (temp[x] == 0) {
                return null;
            }

            temp[x]--;
        }

        return s;
    }

    private String make(String left, int mid) {
        StringBuilder ans = new StringBuilder(left);

        if (mid != -1) {
            ans.append((char) ('a' + mid));
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString();
    }
}