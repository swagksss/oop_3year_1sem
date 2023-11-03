package tridiagonal.matrix;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixValidatorTest {
    // Create a MatrixValidator instance for testing
    private static final MatrixValidator validator = new MatrixValidator();

    @Test
    public void isValidMatrix(){
        // Test a valid tridiagonal matrix
        // Given valid coefficients for the tridiagonal matrix
        List<Double> validA = new ArrayList<>(Arrays.asList(1.0, 3.0, 1.0, 1.0));
        List<Double> validC = new ArrayList<>(Arrays.asList(2.0, 2.0, 4.0, 2.0, 3.0));
        List<Double> validB = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> validF = new ArrayList<>(Arrays.asList(1.0, 2.0, 1.0, 1.0, 1.0));
        TridiagonalMatrix validMatrix = new TridiagonalMatrix(validA, validC, validB, validF);
        assertTrue(validator.isTridiagonal(validMatrix));

        // Test an invalid tridiagonal matrix
        List<Double> notValidB = new ArrayList<>(Arrays.asList(10.0, 10.0, 10.0, 10.0));
        TridiagonalMatrix notValidMatrix = new TridiagonalMatrix(validA, validC, notValidB, validF);
        assertFalse(validator.isTridiagonal(notValidMatrix));
    }
}
