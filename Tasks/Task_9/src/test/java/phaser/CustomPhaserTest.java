package phaser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomPhaserTest {
    private CustomPhaser customPhaser;
    int currentPhase = 0;

    @BeforeEach
    public void setUp() {
        customPhaser = new CustomPhaser(3);
    }

    // Helper method to create a thread that calls arrive()
    void createThread() {
        Thread thread = new Thread(() -> {
            currentPhase = customPhaser.arrive();
        });
        thread.start();
    }

    // Helper method to create a thread that calls arriveAndAwaitAdvance()
    void createThreadWithAwait() {
        Thread thread = new Thread(() -> {
            currentPhase = customPhaser.arriveAndAwaitAdvance();
        });
        thread.start();
    }

    // Helper method to create a thread that calls arriveAndDeregister()
    void createThreadWithDeregister() {
        Thread thread = new Thread(() -> {
            currentPhase = customPhaser.arriveAndDeregister();
        });
        thread.start();
    }

    @Test
    void shouldReachThirdPhase() throws InterruptedException {
        // Create three threads using arriveAndAwaitAdvance()
        createThreadWithAwait();
        createThreadWithAwait();
        createThreadWithAwait();


        synchronized (this) {
            this.wait(2500);
        }

        // Create three more threads using arriveAndAwaitAdvance()
        createThreadWithAwait();
        createThreadWithAwait();
        createThreadWithAwait();


        synchronized (this) {
            this.wait(2500);
        }


        assertEquals(2, currentPhase);
    }

    @Test
    void shouldReachSecondPhaseWithAwait() throws InterruptedException {
        // Create one thread using arrive()
        createThread();

        // Create one thread using arriveAndAwaitAdvance()
        createThreadWithAwait();

        // Create one more thread using arrive()
        createThread();


        synchronized (this) {
            this.wait(2500);
        }

        // Assert that the current phase is 1
        assertEquals(1, currentPhase);
    }

    @Test
    void shouldReachSecondPhase() throws InterruptedException {
        // Create three threads using arrive()
        createThread();
        createThread();
        createThread();


        synchronized (this) {
            this.wait(2500);
        }

        // Assert that the current phase is 1
        assertEquals(1, currentPhase);
    }

    @Test
    void shouldReachThirdPhaseWithDeregister() throws InterruptedException {
        // Call arrive() once outside of a thread
        customPhaser.arrive();

        // Create two threads using arriveAndDeregister()
        createThreadWithDeregister();
        createThreadWithDeregister();

       
        synchronized (this) {
            this.wait(2500);
        }

        // Call arrive() outside of a thread
        currentPhase = customPhaser.arrive();

        // Assert that the current phase is 2
        assertEquals(2, currentPhase);
    }
}
