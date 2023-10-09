package toy.room.toys.exceptions;

// Custom exception class for negative toy price amounts
public class NegativePriceAmount extends Throwable {

    // Constructor to create an instance of NegativePriceAmount with a custom error message
    public NegativePriceAmount(String message) {
        super(message);
    }
}

