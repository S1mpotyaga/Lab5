package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;
import org.example.collectionClasses.readers.ProductReadable;

import java.util.Scanner;

public class Insert extends Command{

    public Insert(Application app){
        super(app, "insert", "Add a new element with given key. The key is entered line by line.\"");
    }

    public void execute(Scanner scanner, String[] command){
        ManagerCollection.addProduct(ProductReadable.readProduct(scanner));
    }
}