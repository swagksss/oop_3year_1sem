package toy.room.toys.dolls;

import toy.room.toys.Toy;

// Subclass representing a Doll toy, extends the Toy class
public class Doll extends Toy {

    // Constructor to create a Doll object with a specified price
    public Doll(float price) {
        // Call the superclass (Toy) constructor with the name "Doll" and the given price
        super("Doll", price);
    }
}
