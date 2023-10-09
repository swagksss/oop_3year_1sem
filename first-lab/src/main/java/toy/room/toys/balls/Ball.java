package toy.room.toys.balls;

import toy.room.toys.Toy;

// Subclass representing a Ball toy, extends the Toy class
public class Ball extends Toy {

    // Constructor to create a Ball object with a specified price
    public Ball(float price) {
        // Call the superclass (Toy) constructor with the name "Ball" and the given price
        super("Ball", price);
    }
}

