package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;
import org.example.collectionClasses.readers.IdReadable;

import java.util.Map;
import java.util.Scanner;

public class RemoveGreaterKey extends Command{

    public RemoveGreaterKey(Application app){
        super(app, "remove_greater_key", "Remove all elements, which are bigger than {element}. {element} is entered line by line.");
    }

    public void execute(Scanner scanner, String[] command){
        System.out.print("Enter the id starting from which you want to delete elements: ");
        int id = IdReadable.idRead(scanner);
        boolean isMet = false;
        for (Map.Entry<Product, Integer> elem : ManagerCollection.getProducts().entrySet()) {
            if (isMet) {
                ManagerCollection.getProducts().remove(elem.getKey(), elem.getValue());
            }
            if (elem.getKey().getId() == id) {
                isMet = true;
            }
        }
    }
}