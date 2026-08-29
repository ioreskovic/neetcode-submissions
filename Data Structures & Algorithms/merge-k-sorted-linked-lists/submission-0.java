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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode SENTINEL = new ListNode();
        ListNode tail = SENTINEL;

        ListNode min = pollMinHead(lists);
        while (min != null) {
            tail.next = min;
            tail = min;
            min = pollMinHead(lists);
        }

        return SENTINEL.next;
    }

    private ListNode pollMinHead(ListNode[] lists) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < lists.length; i++) {
            ListNode examined = lists[i];
            if (examined != null && examined.val < minValue) {
                minValue = examined.val;
                minIndex = i;
            }
        }

        if (minIndex > -1 && minIndex < lists.length) {
            ListNode minNode = lists[minIndex];
            lists[minIndex] = minNode.next;
            minNode.next = null;
            return minNode;
        } else {
            return null;
        }
    }
}
