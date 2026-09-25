import java.util.*;

class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression);
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }

    Set<String> parse(String s) {
        Set<String> res = new HashSet<>();
        Set<String> cur = new HashSet<>();
        cur.add("");

        while (i < s.length() && s.charAt(i) != '}') {
            char c = s.charAt(i);

            if (c == ',') {
                res.addAll(cur);
                cur = new HashSet<>();
                cur.add("");
                i++;
            } else {
                Set<String> next;

                if (c == '{') {
                    i++;
                    next = parse(s);
                    i++;
                } else {
                    next = new HashSet<>();
                    next.add(String.valueOf(c));
                    i++;
                }

                Set<String> temp = new HashSet<>();

                for (String a : cur) {
                    for (String b : next) {
                        temp.add(a + b);
                    }
                }

                cur = temp;
            }
        }

        res.addAll(cur);
        return res;
    }
}