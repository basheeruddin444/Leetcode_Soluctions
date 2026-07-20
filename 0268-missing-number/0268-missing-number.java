class Solution {
    public int missingNumber(int[] nums) {
        
    int total_sum=0;
    for(int num: nums) total_sum+=num;
   int result =total_sum-((nums.length)*(nums.length+1)/2);
   if(result<0)return result*-1;
   else return result;
}
}