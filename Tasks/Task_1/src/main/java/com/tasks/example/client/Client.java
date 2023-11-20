package com.tasks.example.client;

import com.tasks.example.entity.Student;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    // Logger for logging messages
    private static final Logger log = Logger.getLogger(Client.class.getName());

    // Socket channel for communication with the server
    private SocketChannel socketChannel;

    // Buffer for reading and writing data
    private ByteBuffer buffer;

    // Constructor that initializes the socket channel and buffer
    public Client(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.buffer = ByteBuffer.allocate(256);
    }

    // Getter for the buffer
    public ByteBuffer getBuffer() {
        return buffer;
    }

    // Main method to run the client
    public static void main(String[] args) {
        // Create a sample Student object
        Student student = new Student("Sanya", 19, "Taras Shevchenko");

        try {
            // Create a new client and run it with the sample Student object
            new Client(SocketChannel.open(new InetSocketAddress("localhost", 4545))).run(student);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    // Method to run the client, sending a Student object to the server and receiving a response
    public void run(Student student) throws IOException {
        sendStudent(student);
        respondToClient();
    }

    // Method to send a Student object to the server
    public void sendStudent(Student student) throws IOException {
        // Convert the Student object to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(student);
        objectOutputStream.flush();

        // Wrap the byte array in a ByteBuffer and write it to the socket channel
        buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        socketChannel.write(buffer);
    }

    // Method to receive and log a response from the server
    public void respondToClient() throws IOException {
        // Allocate a new ByteBuffer for reading data from the socket channel
        ByteBuffer inputBuffer = ByteBuffer.allocate(256);

        // Read data from the socket channel into the input buffer
        socketChannel.read(inputBuffer);

        // Log the server response
        log.info("Server response: ");
        String response = new String(inputBuffer.array()).trim();
        log.info(response);

        // Clear the buffer for reuse
        buffer.clear();
    }
}
