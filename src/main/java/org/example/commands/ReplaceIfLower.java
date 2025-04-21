package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;
import org.example.collectionClasses.readers.IdReadable;

import java.util.Scanner;

import static java.lang.Math.random;

public class ReplaceIfLower extends Command{

    public ReplaceIfLower(Application app){
        super(app, "replace_if_lower", "Change value by {element}, if new value is lower than old value. {element} is entered line by line.");
    }

    public void execute(Scanner scanner, String[] command){
        System.out.print("Enter changeable id: ");
        int changeId = IdReadable.idRead(scanner);
        Product product = super.getApp().getManagerCollection().findId(changeId);
        while (product == null) {
            System.out.print("Don't exist this id. Enter changeable id again: ");
            changeId = IdReadable.idRead(scanner);
            product = super.getApp().getManagerCollection().findId(changeId);
        }
        int newVal = (int) (random() * 1000);
        if (newVal < ManagerCollection.getProducts().get(product)) {
            ManagerCollection.getProducts().replace(product, newVal);
        }
    }
}
