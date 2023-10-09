package toy.room.toys;

import toy.room.toys.exceptions.NegativePriceAmount;

import java.util.Objects;

// Abstract class representing a generic toy
public abstract class Toy {
    private float price; // Price of the toy
    private String name; // Name of the toy

    // Constructor to create a Toy object with a name and price
    public Toy(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Getter method to retrieve the price of the toy
    public float getPrice() {
        return price;
    }

    // Getter method to retrieve the name of the toy
    public String getName() {
        return name;
    }

    // Setter method to set the price of the toy, with exception handling for negative prices
    public void setPrice(float price) throws NegativePriceAmount {
        if (price < 0) {
            throw new NegativePriceAmount("You can't change price to a negative number");
        }
        this.price = price;
    }

    // Method to rename the toy
    public void renameToy(String name) {
        this.name = name;
    }

    // Override the equals method to compare two toys based on their name and price
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return Float.compare(toy.price, price) == 0 &&
                name.equals(toy.name);
    }

    // Override the hashCode method to generate a hash code based on the name and price
    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }
}
