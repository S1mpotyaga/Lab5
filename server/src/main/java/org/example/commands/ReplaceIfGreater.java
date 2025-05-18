package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.collectionClasses.Product;
import org.example.queries.QueryWrapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static java.lang.Math.random;

public class ReplaceIfGreater extends Command implements Serializable {

    public ReplaceIfGreater(Application app) {
        super(app, Commands.REPLACE_IF_GREATER, "Change value by {element}, if new value is bigger than old value. {element} is entered line by line.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        try {
            int id = (Integer) objStream.readObject();
            Product product = super.getApp().getManagerCollection().findId(id);
            if (product == null) {
                return new Object[]{new QueryWrapper(QueryType.NOT_FOUND_OBJECT), 0};
            }
            int newVal = (int) (random() * 1000);
            if (newVal > super.getApp().getManagerCollection().getProducts().get(product)) {
                super.getApp().getManagerCollection().getProducts().replace(product, newVal);
            }
            return new Object[]{new QueryWrapper(QueryType.ALL_DONE), 0};
        } catch (IOException | ClassNotFoundException e) {
            log.warn("Error while deserializing Integer. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR), 0};
        }
    }
}