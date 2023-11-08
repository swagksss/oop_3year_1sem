package reentrant.lock;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomReentrantLock implements CustomLock {
    private static final Logger log = Logger.getLogger(CustomReentrantLock.class.getName());
    private int lockHoldCount;          // Number of times the lock is held by the current thread.
    private long currentHoldingThreadID; // ID of the thread currently holding the lock.

    public CustomReentrantLock() {
        this.lockHoldCount = 0;
    }

    // Acquire the lock
    public synchronized void lock() {
        if (lockHoldCount == 0) { // Lock is not held by any thread
            lockHoldCount++;
            currentHoldingThreadID = Thread.currentThread().getId();
        } else if (lockHoldCount > 0 &&
                currentHoldingThreadID == Thread.currentThread().getId()) {
            // Lock is held by the current thread, allow reentrant locking
            lockHoldCount++;
        } else { // Lock is held by a different thread
            while (currentHoldingThreadID != Thread.currentThread().getId()) {
                try {
                    this.wait(); // Wait until the lock is released
                    lockHoldCount++;
                    currentHoldingThreadID = Thread.currentThread().getId();
                } catch (InterruptedException e) {
                    log.log(Level.SEVERE, "Exception: ", e);
                }
            }
        }
    }

    // Release the lock
    public synchronized void unlock() {
        if (lockHoldCount == 0) {
            throw new IllegalMonitorStateException(); // Lock is not held by any thread
        }
        lockHoldCount--;
        if (lockHoldCount == 0) {
            notify(); // Notify waiting threads that the lock is released
        }
    }

    // Try to acquire the lock without blocking
    public boolean tryLock() {
        if (lockHoldCount == 0) {
            lock(); // If the lock is not held, acquire it
            return true;
        }
        return false; // Lock is already held by a thread
    }
}
