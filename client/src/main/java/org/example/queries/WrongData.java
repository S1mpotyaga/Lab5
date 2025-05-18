package org.example.queries;

import org.example.Application;

import java.io.IOException;
import java.io.ObjectInputStream;

public class WrongData extends Query<QueryType>{

    public WrongData(Application app){
        super(app, QueryType.WRONG_DATA);
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