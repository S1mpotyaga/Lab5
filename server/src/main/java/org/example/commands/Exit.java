package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Exit extends Command implements Serializable {

    public Exit(Application app) {
        super(app, Commands.EXIT, "Close app.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        return new Object[]{new QueryWrapper(QueryType.EXIT), 0};
    }
}
