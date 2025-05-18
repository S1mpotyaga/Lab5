package org.example;

import lombok.Getter;
import org.example.commands.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

@Getter
public class ManagerCommands {

    public Object[] callCommand(String[] command) {
        for (Commands current : Commands.values()) {
            if (current.toString().equals(command[0])) {
                return (new CommandWrapper(current)).getArgs(command);
            }
        }
        return new Object[]{"Wrong command!"};
    }

    public Object[] callCommandFromFile(Scanner scanner, String[] command){
        for (Commands current : Commands.values()) {
            if (current.toString().equals(command[0])) {
                try {
                    return (new CommandWrapper(current)).getArgs(scanner, command);
                } catch (NoSuchElementException e) {
                    System.err.println("Wrong data in file!!!");
                    break;
                }
            }
        }
        return new Object[]{"Wrong command!"};
    }
}