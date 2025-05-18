package org.example.commands;

import org.example.Application;
import org.example.collectionClasses.Product;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Update extends Command implements Serializable {

    public Update(Application app) {
        super(app, Commands.UPDATE, "update value collection's element, which have id equals given id. {element} is entered line by line.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        try {
            int id = (Integer) objStream.readObject();
            Product newProduct = (Product) objStream.readObject();
            super.getApp().getManagerCollection().setId(id, newProduct);
            return new Object[]{new QueryWrapper(QueryType.ALL_DONE), 0};
        } catch (IOException | ClassNotFoundException e) {
            log.warn("Error while deserializing Product and Integer. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR), 0};
        }
    }
}