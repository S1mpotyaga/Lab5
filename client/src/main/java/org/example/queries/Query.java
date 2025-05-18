package org.example.queries;

import lombok.Getter;
import org.example.Application;
import org.example.ManagerServerQuery;

import java.io.ObjectInputStream;
import java.io.Serializable;


@Getter
public abstract class Query<T> implements Serializable {

    private Application app;
    private QueryType query;

    public Query(Application app, QueryType query){
        this.app = app;
        this.query = query;
    }

    public abstract T execute(ObjectInputStream objStream);
}