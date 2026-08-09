class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int[] fact = new int[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        k--;

        StringBuilder ans = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            int block = fact[i - 1];
            int index = k / block;

            ans.append(nums.get(index));
            nums.remove(index);

            k %= block;
        }

        return ans.toString();
    }
}