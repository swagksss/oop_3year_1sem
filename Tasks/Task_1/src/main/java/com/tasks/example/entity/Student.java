package com.tasks.example.entity;

import java.io.Serializable;
import java.util.Objects;

// Serializable interface is implemented to allow object serialization
public class Student implements Serializable {
    private String name;
    private int age;
    private String nameOfUni;

    // Constructor to initialize the Student object
    public Student(String name, int age, String nameOfUni) {
        this.name = name;
        this.age = age;
        this.nameOfUni = nameOfUni;
    }

    // Default constructor for serialization purposes
    public Student() {}

    // Override the toString() method to provide a meaningful string representation
    @Override
    public String toString() {
        return "Student " +
                "is " + name +
                ", the age is " + age +
                " and the name of university is " + nameOfUni;
    }

    // Override the equals() method to compare Student objects for equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(nameOfUni, student.nameOfUni);
    }

    // Override the hashCode() method to generate a hash code for Student objects
    @Override
    public int hashCode() {
        return Objects.hash(name, age, nameOfUni);
    }
}

