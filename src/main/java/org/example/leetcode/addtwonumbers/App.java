package org.example.leetcode.addtwonumbers;

public class App {
    
      private static class ListNode {
          private final int val;
          private ListNode next;
          private ListNode(final int val) { this.val = val; }
      }
    
    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
        ListNode firstPointer = l1;
        ListNode secondPointer = l2;

        ListNode root = new ListNode(0);
        ListNode curr = root;

        int carry = 0;

        while (firstPointer != null || secondPointer != null) {
            final int x = (firstPointer != null) ? firstPointer.val : 0;
            final int y = (secondPointer != null) ? secondPointer.val : 0;

            final int sum = carry + x + y;
            carry = sum / 10;

            if (firstPointer != null) firstPointer = firstPointer.next;
            if (secondPointer != null) secondPointer = secondPointer.next;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }

        if (carry > 0)
            curr.next = new ListNode(carry);

        return root.next;
    }
}
