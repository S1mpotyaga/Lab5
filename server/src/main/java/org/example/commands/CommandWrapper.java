package org.example.commands;

import lombok.Data;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Data
public class CommandWrapper implements Serializable {

    private Commands command;

    public CommandWrapper(Commands command) {
        this.command = command;
    }

    public Object[] getArgs(String[] args) {
        return null;
    }

    public Object[] getArgs(Scanner scanner, String[] command) throws NoSuchElementException {
        return null;
    }
}