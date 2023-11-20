package com.tasks.example.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    // Logger for logging messages
    private static final Logger log = Logger.getLogger(Server.class.getName());

    // Selector for handling multiple channels
    private Selector selector;

    // Server socket channel for accepting client connections
    private ServerSocketChannel serverSocketChannel;

    // Main method to start the server
    public static void main(String[] args) {
        new Server().run();
    }

    protected void setUp() throws IOException {
        // Open a selector for handling multiple channels
        selector = Selector.open();

        // Open a server socket channel and bind it to a specific address and port
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 4545));
        serverSocketChannel.configureBlocking(false);

        // Register the server socket channel with the selector for accepting connections
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public Server(){}

    public void run() {
        try{
            setUp();

            // Process keys and handle client connections
            processKeys();
        } catch (IOException e){
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    // Method to process keys and handle client connections
    public void processKeys() throws IOException {
        while (true){
            // Wait for events on registered channels
            selector.select();

            // Get the set of selected keys
            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            // Iterate over the selected keys and handle the corresponding events
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                // Accept a new connection if the key is acceptable
                if(selectionKey.isAcceptable()){
                    register();
                }

                // Read and respond to data if the key is readable
                if(selectionKey.isReadable()){
                    deserializeAndRespond(selectionKey);
                }

                // Remove the processed key from the set
                iterator.remove();
            }
        }
    }

    // Method to register a new client connection
    public void register() throws IOException {
        log.info("Connection accepted");
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    // Method to deserialize data received from the client and respond
    public void deserializeAndRespond(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        Serializable clientObject;
        boolean received = false;

        try{
            // Read data from the client into a ByteBuffer
            ByteBuffer buffer = readToBuffer(client);

            // Deserialize the data into a Serializable object
            clientObject = readObject(buffer);
            received = true;
            log.info(clientObject.toString());
        } catch (ClassNotFoundException ex) {
            log.log(Level.SEVERE, "Exception: ", ex);
        }

        // Respond to the client based on whether data was received successfully
        respond(received, client);

        client.close();
    }

    // Method to respond to the client
    protected void respond(boolean received, SocketChannel client) {
        try {
            if(received) {
                // Write a response to the client if data was received
                client.write(ByteBuffer.wrap("Received student".getBytes()));
            } else {
                // Write an error message to the client if something went wrong
                client.write(ByteBuffer.wrap("Something went wrong".getBytes()));
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    // Method to read data from a SocketChannel into a ByteBuffer
    ByteBuffer readToBuffer(SocketChannel client) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer);
        return buffer;
    }

    // Method to deserialize a Serializable object from a ByteBuffer
    Serializable readObject(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        buffer.rewind();
        ObjectInputStream reader = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
        return (Serializable) reader.readObject();
    }
}

