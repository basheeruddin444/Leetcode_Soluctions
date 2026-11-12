class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer,Integer> hm = new HashMap<>();
        for(int num: nums)
        {
            hm.put(num,hm.getOrDefault(num,0)+1);
        }
    int max = nums.length/3;
    List<Integer> result = new ArrayList<>();
    for(int key : hm.keySet())
    {
      if(hm.get(key)>max) result.add(key);
    }
    return result;
    }
}