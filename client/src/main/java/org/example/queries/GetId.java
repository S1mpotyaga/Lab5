package org.example.queries;

import org.example.Application;
import org.example.readers.Readable;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class GetId extends Query<Integer> implements Serializable {

    public GetId(Application app){
        super(app, QueryType.GET_ID);
    }

    @Override
    public Integer execute(ObjectInputStream objStream){
        return Readable.readId();
    }
}
