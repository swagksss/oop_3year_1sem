package toy.room.toys;

import java.util.Comparator;
// Custom comparator for comparing toys based on their prices

public class ToysComparator implements Comparator<Toy> {
    // Override the compare method to compare two toys by their prices

    @Override
    public int compare(Toy o1, Toy o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
