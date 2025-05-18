package org.example.commands;

import org.example.Application;

import java.io.ObjectInputStream;

public class ExecuteScript extends Command{

    public ExecuteScript(Application app){
        super(app, Commands.EXECUTE_SCRIPT, "Execute commands in file.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream){
        return null;
    }
}