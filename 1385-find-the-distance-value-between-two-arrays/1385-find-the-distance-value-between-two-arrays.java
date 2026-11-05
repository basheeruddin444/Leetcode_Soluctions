class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        
        int count =0;
        for(int num1 : arr1)
        {
            boolean fornum1 = true;
            for(int num2: arr2)
            {
              if (Math.abs(num1-num2)<=d) 
              {
                fornum1= false;
                break;
              }
            }
            if(fornum1) count++;
        }
        return count;
    }
}