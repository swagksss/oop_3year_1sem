package toy.room.toys.cars;

import toy.room.toys.Toy;

// Subclass representing a SmallCar toy, extends the Toy class
public class SmallCar extends Toy {

    // Constructor to create a SmallCar object with a specified price
    public SmallCar(float price) {
        // Call the superclass (Toy) constructor with the name "SmallCar" and the given price
        super("SmallCar", price);
    }
}

