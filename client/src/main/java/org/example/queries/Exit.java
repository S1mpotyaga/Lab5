package org.example.queries;

import org.example.Application;

import java.io.ObjectInputStream;
import java.io.Serializable;

import static java.lang.System.exit;

public class Exit extends Query<QueryType> implements Serializable {

    public Exit(Application app){
        super(app, QueryType.EXIT);
    }

    @Override
    public QueryType execute(ObjectInputStream objStream){
        exit(0);
        return QueryType.ALL_DONE;
    }
}