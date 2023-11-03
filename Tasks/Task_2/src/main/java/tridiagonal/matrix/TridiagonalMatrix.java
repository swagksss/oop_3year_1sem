package tridiagonal.matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TridiagonalMatrix {
    private static final Logger log = Logger.getLogger(TridiagonalMatrix.class.getName());
    // Lists to store the coefficients of the tridiagonal matrix
    // diagonal below the main diagonal
    private List<Double> a;
    // main diagonal
    private List<Double> c;
    // diagonal above the main diagonal
    private List<Double> b;
    // right-hand side values of equations
    private List<Double> f;

    // Constructor to initialize the tridiagonal matrix with provided coefficients
    public TridiagonalMatrix(List<Double> a, List<Double> c, List<Double> b, List<Double> f) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.f = f;
    }

    // Getters and setters for the coefficients
    public List<Double> getA() {
        return a;
    }

    public List<Double> getC() {
        return c;
    }

    public List<Double> getB() {
        return b;
    }

    public List<Double> getF() {
        return f;
    }

    public void setA(List<Double> a) {
        this.a = a;
    }

    public void setC(List<Double> c) {
        this.c = c;
    }

    public void setB(List<Double> b) {
        this.b = b;
    }

    public void setF(List<Double> f) {
        this.f = f;
    }

    // Constructor to read the tridiagonal matrix coefficients from a file
    public TridiagonalMatrix(File matrixFile){
        a = new ArrayList<>();
        b = new ArrayList<>();
        c = new ArrayList<>();
        f = new ArrayList<>();
        try{
            // Read the matrix coefficients from the provided file
            Scanner scanner = new Scanner(matrixFile);
            int i = 0;
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] splitedValues = line.split("\\s+");
                // Store the main diagonal coefficients and right-hand side values
                c.add(Double.parseDouble(splitedValues[i]));
                f.add(Double.parseDouble(splitedValues[splitedValues.length-1]));
                // Store the coefficients for the diagonals below and above the main diagonal
                if(i > 0){
                    a.add(Double.parseDouble(splitedValues[i-1]));
                }
                if(i < splitedValues.length - 2){
                    b.add(Double.parseDouble(splitedValues[i+1]));
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            // Log an error if the file is not found
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}

