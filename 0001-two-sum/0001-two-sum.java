class Solution {
    public int[] twoSum(int[] nums, int target) {
        ArrayList<Integer> al = new ArrayList<>();
        
          for(int i =0;i<nums.length;i++)
          {
            int find = target-nums[i];
            if(al.contains(find))
              return new int[]{i,al.indexOf(find)};
            else
              al.add(nums[i]);   
          }
    return null;

    }
}