package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class RemoveKey extends Command implements Serializable {

    public RemoveKey(Application app) {
        super(app, Commands.REMOVE_KEY, "Remove collection's element by key {element}. {element} is entered line by line.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        try {
            int id = (Integer) objStream.readObject();
            super.getApp().getManagerCollection().deleteId(id);
            return new Object[]{new QueryWrapper(QueryType.ALL_DONE), 0};
        } catch (IOException | ClassNotFoundException e) {
            log.warn("Error while deserializing Integer. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR), 0};
        }
    }
}
