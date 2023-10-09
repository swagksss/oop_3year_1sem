package toy.room.toys.cars;

import toy.room.toys.Toy;

// Subclass representing a BigCar toy, extends the Toy class
public class BigCar extends Toy {

    // Constructor to create a BigCar object with a specified price
    public BigCar(float price) {
        // Call the superclass (Toy) constructor with the name "BigCar" and the given price
        super("BigCar", price);
    }
}

