class Solution {
    public boolean checkString(String s) {
        
        boolean b_appired = false;
        for(char ch : s.toCharArray())
        {
            if(ch=='b')
                b_appired=true;
            
            if(ch=='a' && b_appired) return false;
        }
        return true;
    }
}