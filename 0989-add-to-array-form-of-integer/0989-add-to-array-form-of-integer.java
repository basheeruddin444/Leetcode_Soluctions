class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        LinkedList<Integer> result = new LinkedList<>();

        int i = num.length - 1;
        int carry = 0;

        while (i >= 0 || k > 0 || carry > 0) {
            int digit = i >= 0 ? num[i--] : 0;

            int sum = digit + k % 10 + carry;

            result.addFirst(sum % 10);

            carry = sum / 10;
            k /= 10;
        }

        return result;
    }
}