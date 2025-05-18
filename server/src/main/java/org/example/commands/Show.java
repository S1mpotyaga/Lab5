package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.stream.Stream;

public class Show extends Command implements Serializable {

    public Show(Application app) {
        super(app, Commands.SHOW, "Print all elements of a collection to standard output in string form.\"");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        return Stream.concat(Stream.of(new QueryWrapper(QueryType.ALL_DONE), super.getApp().getManagerCollection().getProducts().size()), super.getApp().getManagerCollection().getProducts().entrySet().stream().map(entry -> entry.getKey().toString() + ": " + entry.getValue().toString())).toArray();
    }
}