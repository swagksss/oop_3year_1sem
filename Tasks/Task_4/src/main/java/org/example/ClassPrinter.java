package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassPrinter {
    private final Class<?> classData;

    public ClassPrinter(String name) {
        try {
            // Create a custom class loader to load the specified class by name.
            CustomClassLoader loader = new CustomClassLoader();
            classData = loader.loadClass(name);
        } catch (ClassNotFoundException e) {
            // If the class is not found, wrap the exception and throw a runtime exception.
            throw new RuntimeException(e);
        }
    }

    // Print various information about the loaded class.

    // Print the name of the loaded class.
    public void printName() {
        System.out.println("----------------------Name----------------------");
        System.out.println(classData.getName());
    }

    // Print the package name of the loaded class.
    public void printPackage() {
        System.out.println("----------------------Package----------------------");
        System.out.println(classData.getPackage().getName());
    }

    // Print the superclasses of the loaded class.
    public void printSuperClasses() {
        Class<?> currentSuperClass = classData.getSuperclass();

        System.out.println("----------------------Super classes----------------------");

        while (currentSuperClass != null) {
            System.out.println(currentSuperClass.getName());
            currentSuperClass = currentSuperClass.getSuperclass();
        }
    }

    // Print the interfaces implemented by the loaded class.
    public void printImplementedInterfaces() {
        Class<?>[] interfaces = classData.getInterfaces();

        System.out.println("----------------------Implemented interfaces----------------------");

        for (Class<?> implInterface : interfaces) {
            System.out.println(implInterface.getName());
        }
    }

    // Print the fields (variables) defined in the loaded class.
    public void printFields() {
        System.out.println("----------------------Fields----------------------");

        Field[] fields = classData.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field);
        }
    }

    // Print the constructors of the loaded class.
    public void printConstructors() {
        System.out.println("----------------------Constructors----------------------");

        Constructor<?>[] constructors = classData.getConstructors();

        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }

    // Print the methods defined in the loaded class.
    public void printMethods() {
        System.out.println("----------------------Methods----------------------");

        Method[] methods = classData.getMethods();

        for (Method method : methods) {
            System.out.println(method);
        }
    }

    // Getter method to access the loaded class data.
    public Class<?> getClassData() {
        return classData;
    }
}
