package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.String.format;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create a thread pool with 9 threads.
        ThreadPool threadPool = new ThreadPool(9);
        Counter counter = new Counter(); // Create an instance of the Counter class for calculations.

        long start = System.nanoTime(); // Record the starting time for performance measurement.

        List<Future<Double>> futures = new ArrayList<>(); // Create a list to store Future<Double> objects.

        // Generate 400 asynchronous tasks for calculation and add them to the futures list.
        for (int i = 0; i < 400; i++) {
            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> counter.count(j), threadPool // Asynchronously calculate values using the Counter class and thread pool.
                    ));
        }

        double sumOfFutures = 0; // Initialize a variable to store the sum of the results.

        // Retrieve and sum the results from the completed futures.
        for (Future<Double> future : futures) {
            sumOfFutures += future.get(); // Get the result of each future and add it to the sum.
        }

        // Calculate and print the total execution time and the sum of the calculated values.
        System.out.println(format("Executed by %d s, value: %f",
                (System.nanoTime() - start) / 1000_000_000, sumOfFutures));

        threadPool.shutdown(); // Shut down the custom thread pool.
    }
}
