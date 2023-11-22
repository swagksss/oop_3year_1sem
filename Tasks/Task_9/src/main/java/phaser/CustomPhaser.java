package phaser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomPhaser {
    private static final Logger log = Logger.getLogger(CustomPhaser.class.getName());
    private int parties;         // Total number of parties (threads) expected to participate
    private Integer partiesAwait = 0;  // Number of parties currently awaiting the phase
    private int phase;           // Current phase number

    public CustomPhaser(int parties) {
        this.parties = parties;
        this.partiesAwait = parties;
    }

    // Register a new party
    int register() {
        parties++;
        partiesAwait++;
        return phase;
    }

    // Signal arrival of a party
    int arrive() {
        partiesAwait--;
        int currentPhase = phase;
        synchronized (this) {
            if (partiesAwait == 0) {
                notifyAll();
                partiesAwait = parties;
                phase = currentPhase + 1;
            }
        }
        return phase;
    }

    // Signal arrival of a party and wait for other parties to arrive
    int arriveAndAwaitAdvance() {
        partiesAwait--;
        int currentPhase = phase;
        synchronized (this) {
            while (partiesAwait > 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    log.log(Level.SEVERE, "Exception: ", e);
                }
            }
        }
        partiesAwait = parties;
        phase = currentPhase + 1;
        synchronized (this) {
            notifyAll();
        }
        return phase;
    }

    // Signal arrival of a party and deregister it
    int arriveAndDeregister() {
        --partiesAwait;
        --parties;
        int currentPhase = phase;
        synchronized (this) {
            if (partiesAwait == 0) {
                notifyAll();
                partiesAwait = parties;
                return -1;
            }
        }
        return phase + 1;
    }

    // Get the current phase
    public int getPhase() {
        return phase;
    }
}

