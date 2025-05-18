package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.collectionClasses.Product;
import org.example.queries.QueryWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

public class RemoveGreaterKey extends Command implements Serializable {

    public RemoveGreaterKey(Application app){
        super(app, Commands.REMOVE_GREATER_KEY, "Remove all elements, which are bigger than {element}. {element} is entered line by line.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream){
        try {
            boolean isMet = false;
            int id = (Integer) objStream.readObject();
            for (Map.Entry<Product, Integer> elem : super.getApp().getManagerCollection().getProducts().entrySet()) {
                if (isMet) {
                    super.getApp().getManagerCollection().getProducts().remove(elem.getKey(), elem.getValue());
                }
                if (elem.getKey().getId() == id) {
                    isMet = true;
                }
            }
            return new Object[]{new QueryWrapper(QueryType.ALL_DONE), 0};
        } catch (ClassNotFoundException | IOException e){
            log.warn("Error while deserializing Integer. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR), 0};
        }
    }
}