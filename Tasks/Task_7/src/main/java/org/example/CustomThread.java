package org.example;

public class CustomThread extends Thread {
    private final long duration;      // Тривалість виконання потоку (у мілісекундах)
    private final int number;
    private final CustomCyclicBarrier barrier;  // Спеціальний бар'єр для синхронізації потоків

    public CustomThread(CustomCyclicBarrier barrier, long duration, int number) {
        this.barrier = barrier;     // Ініціалізація бар'єру
        this.duration = duration;   // Ініціалізація тривалості виконання
        this.number = number;       // Ініціалізація номера потоку
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.printf("Thread %d waited for %d ms %n", number, duration);  // Виводимо інформацію про потік
            barrier.await();  // Очікуємо інших потоків на бар'єрі
            System.out.println("Some thread action after barrier");  // Виконуємо дію після досягнення бар'єру
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
