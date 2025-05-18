package org.example.queries;

import org.example.Application;

import java.io.IOException;
import java.io.ObjectInputStream;

public class RepeatLastCommand extends Query<Void>{

    public RepeatLastCommand(Application app){
        super(app, QueryType.REPEAT_LAST_COMMAND);
    }

    @Override
    public Void execute(ObjectInputStream objStream){
        try {
            int n = (Integer) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error." + e.getMessage());
        }
        return null;
    }
}