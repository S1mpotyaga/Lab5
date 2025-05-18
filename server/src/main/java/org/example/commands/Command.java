package org.example.commands;

import lombok.Getter;
import org.example.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

@Getter
public abstract class Command implements Serializable {

    protected static final Logger log = LoggerFactory.getLogger(Command.class);
    private Application app;
    private Commands command;
    private String description = "";

    public Command(Application app, Commands command, String description){
        this.app = app;
        this.command = command;
        this.description = description;
    }

    abstract public Object[] execute(ObjectInputStream objStream);
}