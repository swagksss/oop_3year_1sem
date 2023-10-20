package org.example;

import java.io.Serializable;
import java.time.LocalDate;

// Declare the TestClass as final, indicating that it cannot be extended.
public final class TestClass implements Serializable {
    // Declare instance variables for the class.
    private String name;
    private int age;
    private LocalDate date;

    // Create a constructor for initializing object instances.
    public TestClass(String name, int age, LocalDate date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }

    // Getter method to retrieve the 'name' property.
    public String getName() {
        return name;
    }

    // Setter method to set the 'name' property.
    public void setName(String name) {
        this.name = name;
    }

    // Getter method to retrieve the 'age' property.
    public int getAge() {
        return age;
    }

    // Setter method to set the 'age' property.
    public void setAge(int age) {
        this.age = age;
    }

    // Getter method to retrieve the 'date' property, which is of type LocalDate.
    public LocalDate getDate() {
        return date;
    }

    // Setter method to set the 'date' property.
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
