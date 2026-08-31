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
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] { -1, -1 };
        }

        int index = 1;
        ListNode prev = head;
        ListNode curr = head.next;

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;
        int firstCriticalIndex = -1;
        int previousCriticalIndex = -1;

        while (curr.next != null) {
            int p = prev.val;
            int c = curr.val;
            int n = curr.next.val;

            boolean isCritical = (c < p && c < n) || (c > p && c > n);

            if (isCritical) {

                // First critical point
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = index;
                }

                // From second critical point onward
                if (previousCriticalIndex != -1) {
                    minDistance = Math.min(minDistance, index - previousCriticalIndex);
                }

                // Distance from first critical point
                maxDistance = index - firstCriticalIndex;

                previousCriticalIndex = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (previousCriticalIndex == -1) {
            return new int[] { -1, -1 };
        }

        // Fewer than two critical points
        if (minDistance == Integer.MAX_VALUE) {
            return new int[] { -1, -1 };
        }

        return new int[] { minDistance, maxDistance };
    }
}