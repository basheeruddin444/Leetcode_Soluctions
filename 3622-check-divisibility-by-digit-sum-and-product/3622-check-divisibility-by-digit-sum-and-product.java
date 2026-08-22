class Solution {
    public boolean checkDivisibility(int n) {
        int n1=n;
        int sum=0;
        int product = 1;
        while(n!=0)
        {
            sum+=n%10;
            product*=(n%10);
            n=n/10;
        }
    System.out.println(sum+" "+product);
    int plus =product+sum;
     if(n1%plus==0) return true;
   else return false;

    }
}