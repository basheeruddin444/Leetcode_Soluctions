import java.util.*;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(num, target, 0, 0, 0, "", result);
        return result;
    }

    private void backtrack(String num, long target, int index,
                            long value, long previous,
                            String expression, List<String> result) {

        if (index == num.length()) {
            if (value == target) {
                result.add(expression);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            if (i > index && num.charAt(index) == '0') {
                break;
            }

            String currentString = num.substring(index, i + 1);
            long current = Long.parseLong(currentString);

            if (index == 0) {
                backtrack(num, target, i + 1,
                          current, current,
                          currentString, result);
            } else {
                backtrack(num, target, i + 1,
                          value + current, current,
                          expression + "+" + currentString, result);

                backtrack(num, target, i + 1,
                          value - current, -current,
                          expression + "-" + currentString, result);

                backtrack(num, target, i + 1,
                          value - previous + previous * current,
                          previous * current,
                          expression + "*" + currentString, result);
            }
        }
    }
}