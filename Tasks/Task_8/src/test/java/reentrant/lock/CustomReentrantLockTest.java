package reentrant.lock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomReentrantLockTest {
    private CustomReentrantLock customReentrantLock;

    @BeforeEach
    public void init() {
        customReentrantLock = new CustomReentrantLock();
    }

    @Test
    public void shouldLockAndRelease() throws InterruptedException {
        // Create a new thread to test locking and unlocking
        Thread thread = new Thread(() -> {
            customReentrantLock.lock();     // Acquire the lock
            customReentrantLock.lock();     // Acquire the lock again (reentrant)
            customReentrantLock.unlock();   // Release the lock
            customReentrantLock.unlock();   // Release the lock again
        });
        thread.start(); // Start the thread
        thread.join();  // Wait for the thread to finish
        assertTrue(customReentrantLock.tryLock()); // Try to acquire the lock (should succeed)
        customReentrantLock.unlock(); // Release the lock
    }

    @Test
    public void shouldReturnFalseBecauseTwoLocksOneUnlock() {
        customReentrantLock.lock();     // Acquire the lock
        customReentrantLock.lock();     // Acquire the lock again (reentrant)
        customReentrantLock.unlock();   // Release the lock (once)
        assertFalse(customReentrantLock.tryLock()); // Try to acquire the lock (should fail)
    }
}
