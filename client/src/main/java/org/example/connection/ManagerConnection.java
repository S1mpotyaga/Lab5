package org.example.connection;

import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Getter
public class ManagerConnection {

    private DatagramClient client = null;

    public ManagerConnection() {
        try {
            this.client = new DatagramClient();
        } catch (IOException e) {
            System.err.println("Connection error. " + e.getMessage());
        }
    }

    public void send(Object[] message) throws IOException {
        this.client.sendMessage(message);
    }

    public ObjectInputStream receiveObjectInputStream() {
        try {
            byte[] message = this.client.receiveMessage();
            if (message == null){
                return null;
            }
            return new ObjectInputStream(new ByteArrayInputStream(message));
        } catch (IOException | InterruptedException e) {
            System.err.println("The server is not responding.");
            return null;
        }
    }
}