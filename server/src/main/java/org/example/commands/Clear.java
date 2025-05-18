package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Clear extends Command implements Serializable {

    public Clear(Application app){
        super(app, Commands.CLEAR, "Clear collection.");
    }


    @Override
    public Object[] execute(ObjectInputStream objStream){
        super.getApp().getManagerCollection().clearCollection();
        return new Object[]{new QueryWrapper(QueryType.ALL_DONE), 0};
    }
}