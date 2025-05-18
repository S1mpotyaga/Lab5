package org.example.queries;

import org.example.Application;

import java.io.IOException;
import java.io.ObjectInputStream;

public class WrongCommand extends Query<QueryType>{

    public WrongCommand(Application app){
        super(app, QueryType.WRONG_COMMAND);
    }

    @Override
    public QueryType execute(ObjectInputStream objStream){
        try {
            int n = (Integer) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error." + e.getMessage());
        }
        System.out.println(super.getQuery().toString());
        return QueryType.WRONG_DATA;
    }
}