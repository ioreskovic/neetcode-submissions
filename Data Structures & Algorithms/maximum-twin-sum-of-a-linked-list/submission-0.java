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
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        HashMap<Integer, Integer> halfSums = new HashMap<>();
        int i = 0;

        while (fast.next != null) {
            halfSums.put(i++, slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        int len = 1 + 2*i;
        halfSums.put(i++, slow.val);
        slow = slow.next;

        // slow is at half now, all halfSums are populated
        int maxSum = Integer.MIN_VALUE;
        while (slow != null) {
            maxSum = Math.max(maxSum, halfSums.get(len - i) + slow.val);
            slow = slow.next;
            i++;
        }

        return maxSum;
    }
}