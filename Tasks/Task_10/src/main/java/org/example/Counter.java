package org.example;

public class Counter {
    // This class defines a Counter with a method to perform a mathematical calculation.

    // The count method takes a double 'a' as input and returns a Double result.
    public Double count(double a) {
        // Perform a loop 1,000,000 times to demonstrate a time-consuming operation.
        for (int i = 0; i < 1000000; i++) {
            // Inside the loop, calculate the tangent (tan) of 'a' and add it to 'a'.
            a += Math.tan(a);
        }

        // Return the final value of 'a' after the loop completes.
        return a;
    }
}
