import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<String> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save everything before '('
                stack.push(current.toString());
                current.setLength(0);
            }

            else if (ch == ')') {
                // Reverse content inside parentheses
                current.reverse();

                // Add it to the previous string
                current.insert(0, stack.pop());
            }

            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}