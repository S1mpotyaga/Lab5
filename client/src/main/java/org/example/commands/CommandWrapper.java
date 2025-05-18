package org.example.commands;

import lombok.Data;
import org.example.collectionClasses.Product;
import org.example.readers.ProductReadable;
import org.example.readers.Readable;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.Serializable;

import static java.lang.System.exit;

@Data
public class CommandWrapper implements Serializable {

    private Commands command;

    public CommandWrapper(Commands command) {
        this.command = command;
    }

    public Object[] getArgs(String[] command) {
        switch (this.command) {
            case EXECUTE_SCRIPT -> {
                return (command.length > 1 ? new Object[]{this, command[1]} : new Object[]{this, Readable.readFileName()});
            }
            case INSERT -> {
                return new Object[]{this, ProductReadable.readProduct()};
            }
            case REMOVE_GREATER_KEY, REPLACE_IF_GREATER, REMOVE_KEY, REPLACE_IF_LOWER -> {
                return (command.length > 1 ? new Object[]{this, command[1]} : new Object[]{this, Readable.readId()});
            }
            case UPDATE -> {
                return new Object[]{this, Readable.readId(), ProductReadable.readProduct()};
            }
            case EXIT -> {
                exit(0);
                return new Object[]{this};
            }
            default -> {
                return new Object[]{this};
            }
        }
    }

    public Object[] getArgs(Scanner scanner, String[] command) throws NoSuchElementException {
        switch (this.command) {
            case EXECUTE_SCRIPT -> {
                return (command.length > 1 ? new Object[]{this, command[1]} : new Object[]{this, Readable.readFileName()});
            }
            case INSERT -> {
                return new Object[]{this, ProductReadable.readProduct(scanner)};
            }
            case REMOVE_GREATER_KEY, REPLACE_IF_GREATER, REMOVE_KEY, REPLACE_IF_LOWER -> {
                return (command.length > 1 ? new Object[]{this, command[1]} : new Object[]{this, Readable.readId()});
            }
            case UPDATE -> {
                return new Object[]{this, Readable.readId(), ProductReadable.readProduct()};
            }
            case EXIT -> {
                exit(0);
                return new Object[]{this};
            }
            default -> {
                return new Object[]{this};
            }
        }
    }
}