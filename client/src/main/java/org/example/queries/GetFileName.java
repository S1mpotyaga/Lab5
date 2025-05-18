package org.example.queries;

import org.example.Application;
import org.example.readers.Readable;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class GetFileName extends Query<String> implements Serializable {

    public GetFileName(Application app){
        super(app, QueryType.GET_FILE_NAME);
    }

    @Override
    public String execute(ObjectInputStream objStream){
        return Readable.readFileName();
    }
}