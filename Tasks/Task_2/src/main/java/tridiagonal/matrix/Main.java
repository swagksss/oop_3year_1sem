package tridiagonal.matrix;

import java.io.File;
import java.util.List;

public class Main {
    // Create an instance of MatrixValidator to validate the matrix data
    private static final MatrixValidator matrixValidator = new MatrixValidator();

    public static void main(String[] args) {
        // Create an instance of TridiagonalMatrix by loading data from a file
        TridiagonalMatrix tridiagonalMatrix = new TridiagonalMatrix(new File("src/main/resources/matrix.txt"));

        // Create a Solver instance, passing the matrixValidator to validate the matrix
        Solver solver = new Solver(matrixValidator);

        // Attempt to solve the tridiagonal matrix, and store the result in 'res'
        List<Double> res = solver.solve(tridiagonalMatrix);


        if (res != null) {

            System.out.println(res);
        }
    }
}
