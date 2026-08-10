class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int[] lps = new int[needle.length()];

        for (int i = 1, len = 0; i < needle.length();) {
            if (needle.charAt(i) == needle.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                i++;
            }
        }

        for (int i = 0, j = 0; i < haystack.length();) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                if (j == needle.length())
                    return i - j;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return -1;
    }
}