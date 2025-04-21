package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.readers.IdReadable;

import java.util.Scanner;

public class Update extends Command{

    public Update(Application app){
        super(app, "update", "update value collection's element, which have id equals given id. {element} is entered line by line.");
    }

    public void execute(Scanner scanner, String[] command){
        System.out.print("Enter id product, which you want to change: ");
        int id = IdReadable.idRead(scanner);
        super.getApp().getManagerCollection().setId(scanner, id);
    }
}