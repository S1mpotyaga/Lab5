package org.example.commands;

import org.example.Application;
import org.example.collectionClasses.readers.IdReadable;

import java.util.Scanner;

public class RemoveKey extends Command{

    public RemoveKey(Application app){
        super(app, "remove_key", "Remove collection's element by key {element}. {element} is entered line by line.");
    }

    public void execute(Scanner scanner, String[] command){
        System.out.print("Enter id product, which you want to remove: ");
        int id = IdReadable.idRead(scanner);
        super.getApp().getManagerCollection().deleteId(id);
    }
}
