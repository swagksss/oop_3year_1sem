package org.example;

public class Main {

    public static void main(String[] args) {
        int threadsCount = 5;  // Кількість потоків для створення
        Thread[] threads = new Thread[threadsCount];  // Масив потоків

        // Створення спеціального бар'єру з кількістю потоків та операцією завершення
        CustomCyclicBarrier barrier = new CustomCyclicBarrier(threadsCount, () -> System.out.println("Finish"));

        long minDuration = 1500L;  // Мінімальна тривалість виконання кожного потоку

        // Створення та ініціалізація потоків
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CustomThread(barrier, (i + 1) * minDuration, i);
        }

        // Запуск усіх потоків
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
