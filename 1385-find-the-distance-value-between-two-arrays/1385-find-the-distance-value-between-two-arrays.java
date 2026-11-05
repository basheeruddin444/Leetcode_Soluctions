class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count =0;
        for(int num1 : arr1)
        {
            int left = 0;
            int right = arr2.length-1;
            boolean valid =true;
            while(left<=right)
            {
                int mid = left+(right-left)/2;
                if(arr2[mid]>=num1-d && arr2[mid]<=num1+d)
                {
                     valid = false;
                     break;
                }
                else if(arr2[mid]>num1+d)
                right = mid-1;
                else left = mid+1;
            }
            if(valid) count++;
        }
        return count;
    }
}