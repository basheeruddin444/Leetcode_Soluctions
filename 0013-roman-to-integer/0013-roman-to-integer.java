class Solution {
    public int romanToInt(String s) {
        int result = 0;
        int prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = switch (s.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                default -> 1000;
            };

            if (curr < prev)
                result -= curr;
            else {
                result += curr;
                prev = curr;
            }
        }

        return result;
    }
}