class Solution {
    public int[] resultArray(int[] nums) {
        
        int[] arr1 = new int[nums.length];
        int[] arr2 = new int[nums.length];
        int x=0;
        int y=0;
        arr1[x++]=nums[0];
       
        arr2[y++]=nums[1];
      
        int arr1last=arr1[0];
        int arr2last=arr2[0];
        if(nums.length>2){
        for(int i=2;i<nums.length;i++){
           if(arr1last>arr2last){
            arr1[x++]=nums[i];
            arr1last=nums[i];
           }
           else{
            arr2[y++]=nums[i];
            arr2last=nums[i];
           }
         }
         /*
        System.out.println(x+"-"+y);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        */
       for(int i=0;i<x;i++)
       {
         nums[i]=arr1[i];
       }
      for(int i=0;i<y;i++){
          nums[x+i]=arr2[i];
      }
       

         return nums;
        }
        else{
            return new int[]{};
        }

    }
}