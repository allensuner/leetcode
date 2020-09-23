package org.example.leetcode.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        private Node() {}

        private Node(final int key, final int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addNode(final Node node) {
        // Always add the new node right after head.
        node.prev = this.head;
        node.next = this.head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(final Node node){
        // remove an existing node from the linked list.
        final Node prev = node.prev;
        final Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(final Node node){
        // move certain node in between to the 'head'
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        // pop the current 'tail'
        final Node res = this.tail.prev;
        removeNode(res);
        return res;
    }

    private final Map<Integer, Node> cache = new HashMap<>();
    private int size;
    private final int capacity;
    private final Node head, tail;

    public LRUCache(final int capacity) {
        this.size = 0;
        this.capacity = capacity;

        this.head = new Node();
        this.tail = new Node();

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        final Node node = cache.get(key);
        // if the node cannot be found, return -1
        if (node == null) return -1;
        // move the recently accessed node to the head;
        moveToHead(node);
        return node.value;
    }

    public void put(final int key, final int value) {
        final Node node = cache.get(key);
        if(node == null) {
            // the key does not yet exist in the cache
            final Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            ++size;
            if (size > capacity) {
                // the size cap is reached, so we must make space
                // pop the tail
                final Node tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // key exists, simply update the value
            node.value = value;
            moveToHead(node);
        }
    }
}