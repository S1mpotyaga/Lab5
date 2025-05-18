package org.example.queries;

import org.example.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class FinishReadingFile extends Query<QueryType> implements Serializable {

    public FinishReadingFile(Application app){
        super(app, QueryType.FINISH_READING_FILE);
    }

    @Override
    public QueryType execute(ObjectInputStream objStream){
        try {
            int n = (Integer) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error." + e.getMessage());
        }
        return super.getQuery();
    }
}