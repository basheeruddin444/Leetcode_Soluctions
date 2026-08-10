class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] path = new char[2 * n];
        generate(0, 0, n, path, result);
        return result;
    }

    private void generate(int open, int close, int n, char[] path, List<String> result) {
        int pos = open + close;

        if (pos == path.length) {
            result.add(new String(path));
            return;
        }

        if (open < n) {
            path[pos] = '(';
            generate(open + 1, close, n, path, result);
        }

        if (close < open) {
            path[pos] = ')';
            generate(open, close + 1, n, path, result);
        }
    }
}