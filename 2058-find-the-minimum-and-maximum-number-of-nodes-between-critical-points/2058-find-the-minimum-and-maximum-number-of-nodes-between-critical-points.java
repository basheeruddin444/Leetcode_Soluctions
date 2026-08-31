/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;       // position of first critical point
        int previous = -1;    // position of previous critical point

        int min = Integer.MAX_VALUE;
        int max = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check whether curr is a critical point
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // From second critical point onwards
                if (previous != -1) {
                    min = Math.min(min, index - previous);
                    max = index - first;
                }

                previous = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (min == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{min, max};
    }
}