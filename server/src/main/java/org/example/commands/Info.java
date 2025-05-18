package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.stream.Stream;

public class Info extends Command implements Serializable {

    public Info(Application app){
        super(app,Commands.INFO, "Print information about the collection to standard output.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream){
        return Stream.of(
                new QueryWrapper(QueryType.ALL_DONE), 3,
                "Collection type: " + super.getApp().getManagerCollection().getClass().getTypeName(),
                "Creation date: " + super.getApp().getCreationDate(),
                "Collection size: " + super.getApp().getManagerCollection().getProducts().size()
        ).toArray();
    }
}
