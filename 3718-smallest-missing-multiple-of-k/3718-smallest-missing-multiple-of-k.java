class Solution {
    public int missingMultiple(int[] nums, int k) {
        

       HashSet<Integer> hs = new HashSet<>();

       for(int num : nums)
       {
         hs.add(num);
       }

       for(int i = 1 ; i<=100;i++)
       {
           if(!hs.contains(k*i)) return k*i;
       }
       return 101;
    }
}