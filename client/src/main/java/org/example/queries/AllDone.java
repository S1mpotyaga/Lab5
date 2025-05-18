package org.example.queries;

import org.example.Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class AllDone extends Query<QueryType> implements Serializable {

    public AllDone(Application app) {
        super(app, QueryType.ALL_DONE);
    }

    @Override
    public QueryType execute(ObjectInputStream objStream) {
        try {
            int n = (Integer) objStream.readObject();
            for (int i = 0; i < n; ++i) {
                try {
                    String result = (String) objStream.readObject();
                    System.out.println(result);
                } catch (EOFException e){
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Wrong execute ALL_DONE. " + e.getMessage());
        }
        return QueryType.ALL_DONE;
    }
}