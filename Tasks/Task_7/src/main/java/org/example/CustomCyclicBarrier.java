package org.example;

public class CustomCyclicBarrier {
    private final int threadsCount;   // Кількість потоків, які повинні досягнути бар'єру
    private int awaitCount;           // Лічильник потоків, які чекають на бар'єр
    private final Runnable barrierAction;  // Операція, яку необхідно виконати після досягнення бар'єру

    public CustomCyclicBarrier(int threadsCount, Runnable barrierAction) {
        this.threadsCount = threadsCount;     // Ініціалізація кількості потоків
        this.awaitCount = threadsCount;        // Початкове значення лічильника рівне кількості потоків
        this.barrierAction = barrierAction;    // Ініціалізація операції бар'єру
    }

    public synchronized void await() throws InterruptedException {
        awaitCount--;  // Зменшуємо лічильник потоків, які ще не досягли бар'єру

        if (awaitCount > 0) {
            wait();  // Якщо ще не всі потоки досягли бар'єру, поточний потік очікує
        } else {
            awaitCount = threadsCount;  // Якщо всі потоки досягли бар'єру, скидаємо лічильник ініціалізуємо його знову
            barrierAction.run();  // Виконуємо операцію бар'єру, передбачену в Runnable
            notifyAll();  // Повідомляємо всі потоки, що вони можуть продовжити виконання
        }
    }
}
