package org.example.commands;

import org.example.Application;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.stream.Stream;

public class Help extends Command implements Serializable {

    public Help(Application app) {
        super(app, Commands.HELP, "Display help on available commands.");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream) {
        return Stream.concat(Stream.of(new QueryWrapper(QueryType.ALL_DONE), super.getApp().getManagerCommands().getCommands().size()), super.getApp().getManagerCommands().getCommands().stream().map(x -> x.getCommand().toString() + " - " + x.getDescription())).toArray();
    }
}