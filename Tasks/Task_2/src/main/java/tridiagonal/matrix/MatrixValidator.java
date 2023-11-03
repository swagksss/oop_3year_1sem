package tridiagonal.matrix;

import java.util.List;

public class MatrixValidator {
    //  method checks if the given matrix is tridiagonal
    public boolean isTridiagonal(TridiagonalMatrix matrix) {
        List<Double> a = matrix.getA();
        List<Double> b = matrix.getB();
        List<Double> c = matrix.getC();

        // Ensure that all elements in lists a, b, and c are positive by taking their absolute values
        absElementsInList(a);
        absElementsInList(b);
        absElementsInList(c);

        // Check the tridiagonal matrix conditions
        if (c.get(0) < b.get(0) || c.get(c.size() - 1) < a.get(a.size() - 1)) {
            return false; // The matrix is not tridiagonal if these conditions are not met
        }

        // Loop through the matrix's middle elements
        for (int i = 1; i < c.size() - 2; i++) {
            if (c.get(i) < a.get(i - 1) + b.get(i + 1)) {
                return false; // The matrix is not tridiagonal if this condition is not met
            }
        }

        return true;
    }

    //  method takes the absolute value of each element in a list
    private void absElementsInList(List<Double> list) {
        list.forEach(a -> Math.abs(a));
    }
}

