package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Show extends Command {

    public Show(Application app) {
        super(app, "show", "Print all elements of a collection to standard output in string form.\"");
    }

    public void execute(Scanner scanner, String[] command) {
        Iterator<Map.Entry<Product, Integer>> i = ManagerCollection.getProducts().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            System.out.printf("%s : %s\n", elem.getKey(), elem.getValue());
        }
    }
}