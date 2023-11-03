package tridiagonal.matrix;

public class Coefficients {
    // Declare two private arrays to store coefficients
    private Double[] alpha;
    private Double[] beta;

    // Constructor to initialize the Coefficients object with alpha and beta arrays
    public Coefficients(Double[] alpha, Double[] beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    // Getter method to retrieve the alpha array
    public Double[] getAlpha() {
        return alpha;
    }

    // Getter method to retrieve the beta array
    public Double[] getBeta() {
        return beta;
    }

    // Setter method to set the alpha array
    public void setAlpha(Double[] alpha) {
        this.alpha = alpha;
    }

    // Setter method to set the beta array
    public void setBeta(Double[] beta) {
        this.beta = beta;
    }
}
