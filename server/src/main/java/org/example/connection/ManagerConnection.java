package org.example.connection;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;

@Getter
public class ManagerConnection{

    private static final Logger log = LoggerFactory.getLogger(ManagerConnection.class);

    private DatagramServer server = null;

    public ManagerConnection(){
        try{
            this.server = new DatagramServer();
        } catch (IOException e) {
            log.warn("Connection error. {}", e.getMessage());
        }
    }

    public void send(Object[] message){
        try {
            this.server.sendMessage(message);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ObjectInputStream receiveObjectInputStream() {
        try {
            byte[] message = this.server.receiveMessage();
            return new ObjectInputStream(new ByteArrayInputStream(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}