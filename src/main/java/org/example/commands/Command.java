package org.example.commands;

import lombok.Getter;
import org.example.Application;

import java.util.Scanner;

@Getter
public abstract class Command {
    private Application app;
    private String name = "";
    private String description = "";

    public Command(Application app, String name, String description){
        this.app = app;
        this.name = name;
        this.description = description;
    }

    abstract public void execute(Scanner scanner, String[] command);
}