package reentrant.lock;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        CustomLock lockCustom = new CustomReentrantLock(); // Create a CustomReentrantLock instance
        MyRunnable myRunnable = new MyRunnable(lockCustom); // Create a MyRunnable instance with the lock
        new Thread(myRunnable, "Thread-1").start(); // Start the first thread
        new Thread(myRunnable, "Thread-2").start(); // Start the second thread
    }
}

class MyRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(MyRunnable.class.getName());

    private final CustomLock lockCustom;

    public MyRunnable(CustomLock lockCustom) {
        this.lockCustom = lockCustom; // Initialize the lock in the MyRunnable
    }

    public void run() {
        log.info(Thread.currentThread().getName() + " is waiting to acquire CustomReentrantLock");
        lockCustom.lock(); // Acquire the CustomReentrantLock
        log.info(Thread.currentThread().getName() + " has acquired CustomReentrantLock.");
        try {
            Thread.sleep(3000); // Simulate some work by sleeping
            log.info(Thread.currentThread().getName() + " is sleeping.");
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
        log.info(Thread.currentThread().getName() + " has released CustomReentrantLock.");
        lockCustom.unlock(); // Release the CustomReentrantLock
    }
}
