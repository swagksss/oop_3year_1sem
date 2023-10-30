package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ThreadPoolTest {
    private final Counter counter = new Counter();

    @Test
    public void checkSpeedUp() throws Exception {
        // Get the number of processor cores available on the current system.
        int cores = Runtime.getRuntime().availableProcessors();

        // Measure the time taken with the maximum available cores.
        long timeWithMaxCores = timeWithCores(cores);

        // Measure the time taken with a single core (1 core).
        long timeWithOneCore = timeWithCores(1);

        // Calculate the speedup ratio based on the execution times.
        double speedUp = (double) timeWithOneCore / timeWithMaxCores;

        // Assert that the speedup is greater than 80% of the number of cores.
        Assert.assertTrue(speedUp > cores * 0.8);
    }

    private long timeWithCores(int cores) throws Exception {
        // Create a custom ThreadPool with the specified number of cores.
        ThreadPool threadPool = new ThreadPool(cores);

        // Record the start time for performance measurement.
        long start = System.nanoTime();

        List<Future<Double>> futures = new ArrayList<>();

        // Generate 100 asynchronous tasks for calculation and add them to the futures list.
        for (int i = 0; i < 100; i++) {
            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> counter.count(j),
                            threadPool
                    ));
        }

        double value = 0;

        // Retrieve and sum the results from the completed futures.
        for (Future<Double> future : futures) {
            value += future.get();
        }

        // Calculate and return the total execution time.
        return System.nanoTime() - start;
    }
}
