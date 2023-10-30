package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThreadPool implements Executor {
    // Custom ThreadPool class that implements the Executor interface.

    // A queue to hold the tasks to be executed by the threads.
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();

    // A flag to control whether the threads in the pool should continue running.
    private volatile boolean isRunning = true;

    // Constructor for the ThreadPool class, initializes a specified number of threads.
    public ThreadPool(int numberOfThreads) {
        // Create and start the worker threads.
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Worker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        // Add a new task to the queue for execution.
        if (isRunning) {
            workQueue.offer(command);
        }
    }

    public void shutdown() {
        // Set the isRunning flag to false, indicating that the threads should stop after their current tasks.
        isRunning = false;
    }

    // Inner class that represents the worker threads.
    private final class Worker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                // Continuously check for and execute tasks from the queue as long as the pool is running.
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
