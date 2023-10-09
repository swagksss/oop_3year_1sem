import toy.room.*;
import toy.room.toys.*;
import toy.room.toys.balls.*;
import toy.room.toys.cars.*;
import toy.room.toys.cubes.*;
import toy.room.toys.dolls.*;
import toy.room.toys.exceptions.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a room named "Kids Room"
        Room room = new Room("Kids Room");

        // Initialize a scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        // Start a loop to display the toy room menu and handle user input
        while (keepRunning) {
            System.out.println("====== Toy Room Menu ======");
            System.out.println("1. Add toy to the room");
            System.out.println("2. Sort toys by price");
            System.out.println("3. Find toys in a price interval");
            System.out.println("4. Show all toys");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Adding a toy to the room
                    System.out.print("Enter type of toy (Ball, BigCar, MiddleCar, SmallCar, Cube, Doll): ");
                    String toyType = scanner.next();

                    System.out.print("Enter price of toy: ");
                    float price = scanner.nextFloat();

                    Toy toy = null;
                    switch (toyType) {
                        case "Ball":
                            toy = new Ball(price);
                            break;
                        case "BigCar":
                            toy = new BigCar(price);
                            break;
                        case "MiddleCar":
                            toy = new MiddleCar(price);
                            break;
                        case "SmallCar":
                            toy = new SmallCar(price);
                            break;
                        case "Cube":
                            toy = new Cube(price);
                            break;
                        case "Doll":
                            toy = new Doll(price);
                            break;
                    }

                    if (toy != null) {
                        room.addToy(toy);
                        System.out.println(toyType + " added successfully!");
                    } else {
                        System.out.println("Invalid toy type!");
                    }
                    break;
                case 2:
                    // Sorting toys by price using ToysComparator
                    room.sortToys(new ToysComparator());
                    System.out.println("Toys sorted by price.");
                    break;

                case 3:
                    // Finding toys in a specified price range
                    System.out.print("Enter low price range: ");
                    double lowRange = scanner.nextDouble();
                    System.out.print("Enter high price range: ");
                    double highRange = scanner.nextDouble();

                    List<Toy> foundToys = room.findToysInPriceInterval(lowRange, highRange);
                    System.out.println("Found toys in given price range:");
                    for (Toy t : foundToys) {
                        System.out.println(t.getName() + " - " + t.getPrice());
                    }
                    break;

                case 4:
                    // Displaying all toys in the room
                    System.out.println("All toys in the room:");
                    for (Toy t : room.getToys()) {
                        System.out.println(t.getName() + " - " + t.getPrice());
                    }
                    break;

                case 5:
                    // Exiting the program
                    keepRunning = false;
                    System.out.println("Bye!");
                    break;

                default:
                    // Handling invalid choices
                    System.out.println("Invalid choice!");
            }
        }
        // Close the scanner when the program exits
        scanner.close();
    }
}
