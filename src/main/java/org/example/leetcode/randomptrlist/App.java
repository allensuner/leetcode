package org.example.leetcode.randomptrlist;

public class App {
    public static class Node {
        private final int val;
        private Node next;
        private Node random;

        public Node(final int value) {
            this.val = value;
        }
    }

    // [[7,null],[13,0],[11,4],[10,2],[1,0]]
    public static void main(String[] args) {
        final Node head = new Node(7);
        final Node b = new Node(13);
        final Node c = new Node(11);
        final Node d = new Node(10);
        final Node tail = new Node(1);

        head.next = b;
        b.next = c;
        c.next = d;
        d.next = tail;

        head.random = null;
        b.random = head;
        c.random = tail;
        d.random = c;
        tail.random = head;

        final App app = new App();
        final Node copyHead = app.copyRandomList(head);
        System.out.println(copyHead);
    }

    /**
     * the key here is to copy the next pointers first and then copy the random pointers
     * with the knowledge that the random pointers of the copy are simply the next pointers of the original
     * <br>
     * example:       |---------| <br>
     * original: 1 -> 2 -> 3 -> 4 <br>
     *           |---------| <br>
     *
     *
     * @param head the head of the list
     * @return a deep copy of the list
     */
    public Node copyRandomList(final Node head) {
        if (head == null) {
            return null;
        }
        copyNextPointers(head);
        copyRandomPointers(head);
        return unweave(head);
    }

    private void copyNextPointers(final Node node) {
        if (node == null) {
            return;
        }
        final Node originalNext = node.next;
        final Node copy = new Node(node.val);
        node.next = copy;
        copy.next = originalNext;
        copyNextPointers(originalNext);
    }

    private void copyRandomPointers(final Node node) {
        if (node == null) {
            return;
        }
        final Node originalRandom = node.random;
        final Node copy = node.next;
        final Node copyRandom = (originalRandom == null) ? null : originalRandom.next;
        copy.random = copyRandom;
        final Node next = copy.next;
        copyRandomPointers(next);
    }

    private Node unweave(final Node head) {
        final Node copyHead = head.next;
        reassignNextPointers(head);
        return copyHead;
    }

    private void reassignNextPointers(final Node node) {
        if (node == null) {
            return;
        }
        final Node copy = node.next;
        final Node originalNext = node.next.next;
        final Node copyNext = (originalNext == null) ? null : originalNext.next;
        node.next = originalNext;
        copy.next = copyNext;
        reassignNextPointers(originalNext);
    }
}
