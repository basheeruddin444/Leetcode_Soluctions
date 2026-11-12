class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        

           int increaseLongest = 1;
           int decreaseLongest = 1;
           int maxincrease=0;
           int maxdecrease=0;
            for(int i=1;i<nums.length;i++)
            {
                if(nums[i]>nums[i-1])
                   increaseLongest++;
                else{
                    if(increaseLongest>maxincrease) maxincrease=increaseLongest;
                    increaseLongest=1;
                }
                if(nums[i]<nums[i-1])
                    decreaseLongest++;
                else{
                    if(decreaseLongest>maxdecrease) maxdecrease=decreaseLongest;
                    decreaseLongest=1;
                }

            }
        if(increaseLongest>maxincrease) maxincrease=increaseLongest;
        if(decreaseLongest>maxdecrease) maxdecrease=decreaseLongest;
        if(maxincrease>maxdecrease) return maxincrease;
        else return maxdecrease;

    }
}