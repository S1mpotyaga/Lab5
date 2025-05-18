package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.collectionClasses.Product;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class MinByManufacturer extends Command implements Serializable {

    public MinByManufacturer(Application app) {
        super(app, Commands.MIN_BY_MANUFACTURER, "Print any collection's key, which value 'manufacturer' is minimal.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        return Stream.concat(Stream.of(new QueryWrapper(QueryType.ALL_DONE), 1), Stream.of("Minimum is: " + super.getApp().getManagerCollection().getProducts().keySet().stream().max(Comparator.comparing(Product::getCoordinates, (c1, c2) -> c1.isBig(c2) ? 1 : -1)).orElse(null).toString())).toArray();
    }
}