package org.example.queries;

import org.example.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Error extends Query<QueryType> implements Serializable {

    public Error(Application app){
        super(app, QueryType.ERROR);
    }

    @Override
    public QueryType execute(ObjectInputStream objStream){
        try {
            int n = (Integer) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        System.out.println("The server is temporarily unresponsive.");
        return QueryType.ERROR;
    }
}