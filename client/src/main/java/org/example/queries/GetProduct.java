package org.example.queries;

import org.example.Application;
import org.example.collectionClasses.Product;
import org.example.readers.ProductReadable;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class GetProduct extends Query<Product> implements Serializable {

    public GetProduct(Application app){
        super(app, QueryType.GET_PRODUCT);
    }

    @Override
    public Product execute(ObjectInputStream objStream){
        return ProductReadable.readProduct();
    }
}