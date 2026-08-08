import java.util.*;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int[] heights = new int[matrix[0].length];
        int maxArea = 0;

        for (char[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                heights[i] = row[i] == '1' ? heights[i] + 1 : 0;
            }

            maxArea = Math.max(maxArea, largestRectangle(heights));
        }

        return maxArea;
    }

    private int largestRectangle(int[] heights) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= heights.length; i++) {
            int current = i == heights.length ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    private int maxArea = 0;
}