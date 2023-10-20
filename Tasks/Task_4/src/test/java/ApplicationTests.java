import org.example.ClassPrinter;
import org.example.CustomClassLoader;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTests {
    // Test case to validate the behavior of the custom class loader when loading a non-Java class.
    @Test
    void testClassLoaderOnNonJavaClass() {
        // Create a custom class loader.
        CustomClassLoader loader = new CustomClassLoader();

        try {
            // Load the "org.example.TestClass" class using the custom class loader.
            Class<?> loadedClass = loader.loadClass("org.example.TestClass");
            Field[] fields = loadedClass.getDeclaredFields();

            // Define the expected and actual field names.
            List<String> expectedFieldsNames = List.of("name", "age", "date");
            List<String> actualFieldsNames = new ArrayList<>();

            // Extract and store the field names from the loaded class.
            for (Field field : fields) {
                actualFieldsNames.add(field.getName());
            }

            int classModifiers = loadedClass.getModifiers();

            // Assert that the loaded class has the expected modifiers.
            assertTrue(Modifier.isPublic(classModifiers));
            assertFalse(Modifier.isAbstract(classModifiers));
            assertTrue(Modifier.isFinal(classModifiers));

            // Assert that the field names match the expected names.
            assertEquals(expectedFieldsNames, actualFieldsNames);

            // Assert that the loaded class implements the Serializable interface.
            assertEquals(Serializable.class, loadedClass.getInterfaces()[0]);
        } catch (ClassNotFoundException e) {
            fail();
        }
    }

    // Test case to validate the behavior of the custom class loader when loading a Java class.
    @Test
    void testClassLoaderOnJavaClass() {
        // Create a custom class loader.
        CustomClassLoader loader = new CustomClassLoader();

        try {
            // Load the "java.util.ArrayList" class using the custom class loader.
            Class<?> loadedClass = loader.loadClass("java.util.ArrayList");

            // Assert that the loaded class inherits from AbstractList.
            assertEquals(AbstractList.class, loadedClass.getSuperclass());

            int classModifiers = loadedClass.getModifiers();

            // Assert that the loaded class has the expected modifiers.
            assertTrue(Modifier.isPublic(classModifiers));
            assertFalse(Modifier.isAbstract(classModifiers));
            assertFalse(Modifier.isFinal(classModifiers));

            // Assert that the loaded class implements multiple interfaces.
            assertEquals(4, loadedClass.getInterfaces().length);
        } catch (ClassNotFoundException e) {
            fail();
        }
    }

    // Test case to print information about a class using the ClassPrinter.
    @Test
    void testPrint() {
        // Create a ClassPrinter to print information about the "org.example.TestClass" class.
        ClassPrinter printer = new ClassPrinter("org.example.TestClass");
        printer.printAll();
    }
}
