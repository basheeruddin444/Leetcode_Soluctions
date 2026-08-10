class Solution {
    public String simplifyPath(String path) {
        String[] stack = new String[path.length()];
        int top = 0;

        for (String part : path.split("/")) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }

            if (part.equals("..")) {
                if (top > 0) top--;
            } else {
                stack[top++] = part;
            }
        }

        if (top == 0) return "/";

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < top; i++) {
            result.append('/').append(stack[i]);
        }

        return result.toString();
    }
}