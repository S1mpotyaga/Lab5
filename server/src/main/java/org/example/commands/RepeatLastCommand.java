package org.example.commands;

import org.example.Application;

import java.io.ObjectInputStream;

public class RepeatLastCommand extends Command{

    public RepeatLastCommand(Application app){
        super(app, Commands.REPEAT_LAST_COMMAND, "repeat last command");
    }

    @Override
    public Object[] execute(ObjectInputStream objStream){
        return super.getApp().getHistory().getLastCommand().execute(objStream);
    }
}