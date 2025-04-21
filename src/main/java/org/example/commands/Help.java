package org.example.commands;

import org.example.Application;

import java.util.Scanner;

public class Help extends Command{

    public Help(Application app) {
        super(app, "help", "Display help on available commands.");
    }

    public void execute(Scanner scanner, String[] command){
        for (Command current: super.getApp().getManagerCommands().getCommands()){
            System.out.printf("%s - %s\n", current.getName(), current.getDescription());
        }
    }
}
