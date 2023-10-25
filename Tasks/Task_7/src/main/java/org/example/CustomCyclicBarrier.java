package org.example;

public class CustomCyclicBarrier {
    private final int threadsCount;   // The number of threads that should reach the barrier
    private int awaitCount;           // Counter for threads waiting at the barrier
    private final Runnable barrierAction;  // The operation to be executed after reaching the barrier

    public CustomCyclicBarrier(int threadsCount, Runnable barrierAction) {
        this.threadsCount = threadsCount;     // Initialize the number of threads
        this.awaitCount = threadsCount;        // Set the initial count equal to the number of threads
        this.barrierAction = barrierAction;    // Initialize the barrier operation
    }

    public synchronized void await() throws InterruptedException {
        awaitCount--;  // Decrement the count of threads that haven't reached the barrier yet

        if (awaitCount > 0) {
            wait();  // If not all threads have reached the barrier, the current thread waits
        } else {
            awaitCount = threadsCount;  // If all threads have reached the barrier, reset the counter
            barrierAction.run();  // Execute the barrier operation specified in the Runnable
            notifyAll();  // Notify all threads that they can continue execution
        }
    }
}
