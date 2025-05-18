package org.example.queries;

import org.example.Application;
import org.example.readers.Readable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class NotFoundObject extends Query<Query<?>>{

    public NotFoundObject(Application app){
        super(app, QueryType.NOT_FOUND_OBJECT);
    }

    @Override
    public Query<?> execute(ObjectInputStream objStream){
        try {
            int n = (Integer) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error." + e.getMessage());
        }
        System.out.println("Do you want to repeat last command (Yes/No)?");
        String result = Readable.readAnswer();
        if (result.equalsIgnoreCase("yes")){
            return new RepeatLastCommand(super.getApp());
        }else {
            return null;
        }
    }
}