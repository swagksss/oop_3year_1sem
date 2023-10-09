package toy.room.toys.cubes;

import toy.room.toys.Toy;

// Subclass representing a Cube toy, extends the Toy class
public class Cube extends Toy {

    // Constructor to create a Cube object with a specified price
    public Cube(float price) {
        // Call the superclass (Toy) constructor with the name "Cube" and the given price
        super("Cube", price);
    }
}
