package org.example;

public class CustomThread extends Thread {
    private final long duration;      // Duration of thread execution (in milliseconds)
    private final int number;         // Thread number
    private final CustomCyclicBarrier barrier;  // Special barrier for thread synchronization

    public CustomThread(CustomCyclicBarrier barrier, long duration, int number) {
        this.barrier = barrier;     // Initialize the barrier
        this.duration = duration;   // Initialize the execution duration
        this.number = number;       // Initialize the thread number
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.printf("Thread %d waited for %d ms %n", number, duration);  // Display information about the thread
            barrier.await();  // Wait for other threads at the barrier
            System.out.println("Some thread action after the barrier");  // Execute an action after reaching the barrier
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
