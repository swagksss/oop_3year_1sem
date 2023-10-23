package org.example;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Create a NonBlockingQueue to hold Integer values.
        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();

        // Create and start three threads to push values into the queue.
        Thread t1 = new Thread(() -> queue.push(1));
        Thread t2 = new Thread(() -> queue.push(2));
        Thread t3 = new Thread(() -> queue.push(3));

        t1.start();
        t2.start();
        t3.start();

        // Wait for the three push threads to complete.
        t1.join();
        t2.join();
        t3.join();

        // Create new threads to pop and print values from the queue.
        t1 = new Thread(() -> System.out.println(queue.pop()));
        t2 = new Thread(() -> System.out.println(queue.pop()));
        t3 = new Thread(() -> System.out.println(queue.pop()));

        t1.start();
        t2.start();
        t3.start();

        // Wait for the pop threads to complete.
        t1.join();
        t2.join();
        t3.join();

        // Check if the last pop operation returned null and print the result.
        System.out.println("Is null last: " + (queue.pop() == null ? "Yes" : "No"));
    }
}
