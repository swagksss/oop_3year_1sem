package org.example;

public class Main {

    public static void main(String[] args) {
        int threadsCount = 5;  // Number of threads to create
        Thread[] threads = new Thread[threadsCount];  // Array of threads

        // Creating a special barrier with the number of threads and a finishing operation
        CustomCyclicBarrier barrier = new CustomCyclicBarrier(threadsCount, () -> System.out.println("Finish"));

        long minDuration = 1500L;  // Minimum duration for each thread's execution

        // Creating and initializing threads
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CustomThread(barrier, (i + 1) * minDuration, i);
        }

        // Starting all threads
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
