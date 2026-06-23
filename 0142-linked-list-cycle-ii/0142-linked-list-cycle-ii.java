/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        
      ListNode fast = head;
      ListNode slow = head;
     
      while(fast!=null && fast.next!=null)
      {
        fast=fast.next.next;
        slow = slow.next;
        if(fast==slow)
        { 
            ListNode temp1 = head;
            ListNode temp2 = fast;
        
            while(temp1!=temp2)
            {
              
                temp1=temp1.next;
                temp2=temp2.next;
            }
            return temp1;
        }
      }
      return null;
      

    }
}