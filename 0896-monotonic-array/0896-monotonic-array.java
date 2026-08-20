class Solution {
    public boolean isMonotonic(int[] nums) {
        if(nums.length==1) return true;
        boolean isit=false;
        boolean isit1=false;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]>=nums[i-1]) 
            {
                 isit=true;
            }
            else{
                isit=false;
                break;
            }
           
        }
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]<=nums[i-1])
            {
                isit1=true;
            }
            else{
                isit1=false;
                break;
            }
           
        }
         
         if(isit || isit1) return true;
         else return false;
    }
}