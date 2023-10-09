package toy.room;

import toy.room.toys.Toy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Room {
    private String name = "";
    private List<Toy> toys;
    private Children typeOfRoom;
    private double moneyLimit;

    // Constructor for creating a Room object with a name
    public Room(String name) {
        this.name = name;
        toys = new ArrayList<>();
        typeOfRoom = Children.DEFAULT;
        moneyLimit = 1000;
    }

    // Constructor for creating a Room object with a name, money limit, a list of toys, and type of room
    public Room(String name, double moneyLimit, List<Toy> newToys, Children typeOfRoom) {
        this.name = name;
        this.moneyLimit = moneyLimit;
        this.toys = new ArrayList<>();

        // Check if toys can be added based on the money limit
        if (canAddToysToRoom(newToys)) {
            this.toys.addAll(newToys);
        } else {
            System.out.println("You can't add new toys because of money limit.");
        }
        this.typeOfRoom = typeOfRoom;
    }

    // Private method to check if toys can be added to the room based on the money limit
    private boolean canAddToysToRoom(List<Toy> toys) {
        double wholeSummaOfToys = 0;
        for (Toy toy : toys) {
            wholeSummaOfToys += toy.getPrice();
        }
        return wholeSummaOfToys <= this.getMoneyLimit();
    }

    // Constructor for creating a Room object with a name and a list of toys
    public Room(String name, List<Toy> toys) {
        this.name = name;
        this.toys = new ArrayList<>();

        // Check if toys can be added based on the money limit
        if (canAddToysToRoom(toys)) {
            this.toys.addAll(toys);
        }
    }

    // Default constructor
    public Room() {
        this.toys = new ArrayList<>();
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for toys
    public List<Toy> getToys() {
        return toys;
    }

    // Getter method for typeOfRoom
    public Children getTypeOfRoom() {
        return typeOfRoom;
    }

    // Getter method for moneyLimit
    public double getMoneyLimit() {
        return moneyLimit;
    }

    // Setter method for toys
    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    // Method to change the type of room
    public void changeTypeOfRoom(Children typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    // Method to change the money limit
    public void changeMoneyLimit(double moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    // Method to rename the room
    public void renameRoom(String name) {
        this.name = name;
    }

    // Method to add a toy to the room
    public void addToy(Toy toy) {
        if (calculateCurrentValueOfToys() + toy.getPrice() <= this.getMoneyLimit()) {
            toys.add(toy);
            this.moneyLimit += toy.getPrice();
        }
    }

    // Private method to calculate the current value of toys in the room
    private double calculateCurrentValueOfToys() {
        double summa = 0;
        for (Toy toy : this.getToys()) {
            summa += toy.getPrice();
        }
        return summa;
    }

    // Method to remove a toy from the room based on its name
    public void removeToy(String toyName) {
        toys.removeIf(toy -> toyName.equals(toy.getName()));
    }

    // Method to sort toys using a comparator
    public void sortToys(Comparator<Toy> comparator) {
        toys.sort(comparator);
    }

    // Method to find toys within a specified price range
    public List<Toy> findToysInPriceInterval(double low, double high) {
        List<Toy> result = new ArrayList<>();
        for (Toy toy : toys) {
            if (toy.getPrice() >= low && toy.getPrice() <= high) {
                result.add(toy);
            }
        }
        return result;
    }
}
