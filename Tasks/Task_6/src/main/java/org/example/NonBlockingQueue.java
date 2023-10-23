package org.example;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingQueue<T> {
    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;

    public NonBlockingQueue() {
        // Create a NonBlockingQueue with dummy nodes for head and tail.
        Node<T> dummy = new Node<>();
        this.head = new AtomicReference<>(dummy);
        this.tail = new AtomicReference<>(dummy);
    }

    private static class Node<T> {
        T value;
        AtomicReference<Node<T>> next;

        Node() {
            this.next = new AtomicReference<>();
        }
        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>();
        }
    }

    public void push(T value) {
        // Create a new node with the given value.
        Node<T> toPush = new Node<>(value);
        Node<T> currentTailNode;
        Node<T> currentNextNode;

        while (true) {
            currentTailNode = tail.get();
            currentNextNode = currentTailNode.next.get();

            if (currentTailNode == tail.get()) {
                if (currentNextNode == null) {
                    // If the current tail node's next is null, set it to the new node.
                    if (currentTailNode.next.compareAndSet(null, toPush)) {
                        break;
                    }
                } else {
                    // If the current tail node's next is not null, update the tail to the next node.
                    tail.compareAndSet(currentTailNode, currentNextNode);
                }
            }
        }

        // Set the new node as the tail.
        tail.compareAndSet(currentTailNode, toPush);
    }

    public T pop() {
        T result;
        Node<T> currentHeadNode;
        Node<T> currentTailNode;
        Node<T> currentNextNode;

        while (true) {
            currentHeadNode = head.get();
            currentTailNode = tail.get();
            currentNextNode = currentHeadNode.next.get();

            if (currentHeadNode == head.get()) {
                if (currentHeadNode == currentTailNode) {
                    if (currentNextNode == null) {
                        // If head and tail are the same and the next node is null, the queue is empty.
                        return null;
                    }

                    // If head and tail are the same, update the tail to the next node.
                    tail.compareAndSet(currentTailNode, currentNextNode);
                } else {
                    result = currentNextNode.value;

                    // Try to update the head to the next node and break the loop.
                    if (head.compareAndSet(currentHeadNode, currentNextNode)) {
                        break;
                    }
                }
            }
        }

        // Return the value that was popped from the queue.
        return result;
    }
}
