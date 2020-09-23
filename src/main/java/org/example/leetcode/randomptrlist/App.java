package org.example.leetcode.randomptrlist;

import java.util.HashMap;
import java.util.Map;

public class App {
    static class Node {
        private final int val;
        private Node next;
        private Node random;

        public Node(final int value) {
            this.val = value;
        }
    }

    public Node copyRandomList(final Node head) {
        if (head == null)
            return null;

        copyNextPointers(head);
        copyRandomPointers(head);

        Node oldPointer = head;
        Node copyPointer = head.next;
        final Node copyHead = copyPointer;

        while (oldPointer != null) {
            oldPointer.next = oldPointer.next.next;
            if (copyPointer != null)
                copyPointer.next = (copyPointer.next != null) ? copyPointer.next.next : null;
            oldPointer = oldPointer.next;
            if (copyPointer != null)
                copyPointer = copyPointer.next;
        }

        return copyHead;
    }

    private void copyRandomPointers(final Node node) {
        if (node.next.next != null) {
            final Node copy = node.next;
            copy.random = (node.random != null) ? node.random.next : null;
            copyNextPointers(node.next.next);
        }
    }

    private void copyNextPointers(final Node node) {
        if (node.next != null) {
            final Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
            copyNextPointers(copy.next);
        }
    }
}
