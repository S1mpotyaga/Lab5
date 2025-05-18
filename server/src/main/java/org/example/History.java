package org.example;

import lombok.Getter;
import org.example.commands.Command;

import java.util.ArrayList;

@Getter
public class History {

    private ArrayList<Command> history = new ArrayList<Command>(0);

    public void addCommand(Command command){
        this.history.add(command);
    }

    public Command getLastCommand(){
        return this.history.get(history.size() - 1);
    }
}