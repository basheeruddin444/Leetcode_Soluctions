class Solution {
    public int missingMultiple(int[] nums, int k) {
        
          for(int i = k ; ; i+=k)
          {
             boolean found = false;
             for(int j=0;j<nums.length;j++)
             {
                if(nums[j]==i)
                {
                    found = true;
                    break;
                }

             }
             if(!found) return i;
          }
       
    }
}