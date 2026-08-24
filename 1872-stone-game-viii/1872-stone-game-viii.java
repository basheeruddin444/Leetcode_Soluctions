class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

         
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

       
        int d = stones[n - 1];

        
        for (int i = n - 2; i > 0; i--) {
            d = Math.max(d, stones[i] - d);
        }

        return d;
    }
}