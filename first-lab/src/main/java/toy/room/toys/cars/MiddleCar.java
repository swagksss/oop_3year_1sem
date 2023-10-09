package toy.room.toys.cars;

import toy.room.toys.Toy;

// Subclass representing a MiddleCar toy, extends the Toy class
public class MiddleCar extends Toy {

    // Constructor to create a MiddleCar object with a specified price
    public MiddleCar(float price) {
        // Call the superclass (Toy) constructor with the name "MiddleCar" and the given price
        super("MiddleCar", price);
    }
}

