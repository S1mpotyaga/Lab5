package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.stream.Stream;

public class PrintFieldAscendingPrice extends Command implements Serializable {

    public PrintFieldAscendingPrice(Application app) {
        super(app, Commands.PRINT_FIELD_ASCENDING_PRICE, "Print all values field 'price' in ascending order.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        return Stream.concat(Stream.of(new QueryWrapper(QueryType.ALL_DONE), super.getApp().getManagerCollection().getProducts().size()), super.getApp().getManagerCollection().getProducts().keySet().stream().map(pr -> pr.getPrice().toString())).toArray();
    }
}