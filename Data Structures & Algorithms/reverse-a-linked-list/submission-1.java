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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        if (head.next == null) {
            return  head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;

        while (curr != null) {
            curr.next = prev;

            prev = curr;
            curr = next;
            next = next == null ? null : next.next;
        }

        return prev;
    }

    // a    b    c
    //      1 -> 2 -> 3 -> 4
    // a    b    c
    // n <- 1    2 -> 3 -> 4
    //      a    b    c
    // n <- 1 <- 2    3 -> 4
    //           a    b    c
    // n <- 1 <- 2 <- 3    4
    //                a    b    c
    // n <- 1 <- 2 <- 3 <- 4
}
