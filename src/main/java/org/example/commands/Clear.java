package org.example.commands;

import org.example.Application;

import java.util.Scanner;

public class Clear extends Command{

    public Clear(Application app){
        super(app, "clear", "Clear collection.");
    }

    public void execute(Scanner scanner, String[] command){
        super.getApp().getManagerCollection().clearCollection();
    }
}
