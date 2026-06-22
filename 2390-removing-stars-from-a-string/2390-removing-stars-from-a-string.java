class Solution {
    public String removeStars(String s) {
        /// soluction 1
        /*
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='*')
              stack.pop();
            else
              stack.push(s.charAt(i));
        }
        String result="";
          for (Character item : stack) {
            result+=item;
        }
        return result;*/
        /// soluction 2
        StringBuilder str = new StringBuilder();
        for(char ch : s.toCharArray())
        {
            if(ch=='*')
              str.deleteCharAt(str.length()-1);
            else
             str.append(ch);
        }
        return str.toString();
    }
}