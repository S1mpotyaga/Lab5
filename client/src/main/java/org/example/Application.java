package org.example;

import lombok.Getter;
import org.example.commands.CommandWrapper;
import org.example.commands.Commands;
import org.example.commands.ExecuteScript;
import org.example.connection.ManagerConnection;
import org.example.readers.Readable;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * A console application class that implements interactive management
 * of a collection of Product objects.
 */

@Getter
public class Application {

    private final ManagerServerQuery managerServer;
    private final ManagerCommands managerCommands;
    private ManagerConnection managerConnection;

    public Application() {
        this.managerServer = new ManagerServerQuery(this);
        this.managerCommands = new ManagerCommands();
        this.managerConnection = new ManagerConnection();
        start();
    }

    public void start() {
        while (true) {
            System.out.println("Enter command.");
            String tmp = "";
            try {
                tmp = Readable.scanner.nextLine();
            } catch (NoSuchElementException e){
                System.exit(0);
            }
            String[] command = tmp.split(" ");
            Object[] msg = this.managerCommands.callCommand(command);
            if (msg[0] instanceof String) {
                System.out.println((String) msg[0]);
                continue;
            }
            if (((CommandWrapper) msg[0]).getCommand().equals(Commands.EXECUTE_SCRIPT)) {
                ExecuteScript.execute(this, (String) msg[1]);
                continue;
            }
            sendAndProcessResponse(msg);
        }
    }

    public void startFile(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String[] command = scanner.nextLine().split(" ");
            Object[] msg = this.managerCommands.callCommandFromFile(scanner, command);
            if (msg[0] instanceof String) {
                System.err.println((String) msg[0]);
                continue;
            }
            if (((CommandWrapper) msg[0]).getCommand().equals(Commands.EXECUTE_SCRIPT)) {
                ExecuteScript.execute(this, (String) msg[1]);
                continue;
            }
            sendAndProcessResponse(msg);
        }
    }

    private void sendAndProcessResponse(Object[] answer) {
        while (true) {
            try {
                this.managerConnection.send(answer);
            } catch (IOException e) {
                System.err.println("Connection error.");
                break;
            }
            ObjectInputStream objStream = this.managerConnection.receiveObjectInputStream();
            if (objStream == null) {
                System.err.println("Connection error.");
                break;
            }
            try {
                this.managerServer.callQuery(objStream);
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Connection error." + e.getMessage());
                break;
            }
        }
    }
}