package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
    // Override the loadClass method to load custom classes.
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // Check if the class name starts with "java." (Java standard library).
        // If it does, delegate the loading to the parent class loader.
        if (name.startsWith("java.")) {
            return super.loadClass(name);
        }

        // Load the class data and define the class.
        byte[] classData = loadClassData(name);
        return defineClass(name, classData, 0, classData.length);
    }

    // Helper method to load class data from the class file.
    private byte[] loadClassData(String name) {
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream(name.replace(".", "/") + ".class");) {
            // Create a ByteArrayOutputStream to read and store the class data.
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int current = 0;

            // Read the class data byte by byte and write it to the output stream.
            while ((current = input.read()) != -1) {
                output.write(current);
            }

            // Convert the output stream content to a byte array and return it.
            return output.toByteArray();
        } catch (IOException e) {
            // If there's an error loading the class data, wrap it in a runtime exception and throw.
            throw new RuntimeException(e);
        }
    }
}
