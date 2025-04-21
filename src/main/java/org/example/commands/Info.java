package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;

import java.util.Scanner;

public class Info extends Command{

    public Info(Application app){
        super(app,"info", "Print information about the collection to standard output.");
    }

    public void execute(Scanner scanner, String[] command){
        System.out.printf("Collection type: %s\n", getApp().getManagerCollection().getClass().getTypeName());
        System.out.printf("Creation date: %s\n", getApp().getCreationDate());
        System.out.printf("Collection size: %d\n", ManagerCollection.getProducts().size());
    }
}
