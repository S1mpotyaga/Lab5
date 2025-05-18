package org.example.commands;

import org.example.Application;
import org.example.collectionClasses.Product;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Insert extends Command implements Serializable {

    public Insert(Application app){
        super(app, Commands.INSERT, "Add a new element with given key. The key is entered line by line.\"");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream){
        try{
            Product product = (Product) objStream.readObject();
            super.getApp().getManagerCollection().addProduct(product);
            return new Object[]{new QueryWrapper(QueryType.ALL_DONE), 0};
        } catch (IOException | ClassNotFoundException e){
            log.warn("Error while deserializing Product. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR), 0};
        }
    }
}