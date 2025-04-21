package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PrintFieldAscendingPrice extends Command{

    public PrintFieldAscendingPrice(Application app){
        super(app, "print_field_ascending_price", "Print all values field 'price' in ascending order.");
    }

    public void execute(Scanner scanner, String[] command){
        Iterator<Map.Entry<Product, Integer>> i = ManagerCollection.getProducts().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            System.out.println(elem.getKey().getPrice());
        }
    }
}