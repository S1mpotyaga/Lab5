package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MaxByCoordinates extends Command{

    public MaxByCoordinates(Application app){
        super(app, "max_by_coordinates", "Print any collection's key, which value 'coordinates' is maximal.");
    }

    public void execute(Scanner scanner, String[] command){
        Iterator<Map.Entry<Product, Integer>> i = ManagerCollection.getProducts().entrySet().iterator();
        boolean isMet = false;
        Product mn = ((Map.Entry<Product, Integer>) i.next()).getKey();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (elem.getKey().getCoordinates().isBig(mn.getCoordinates())) {
                mn = elem.getKey();
            }
        }
        System.out.println("Maximum is: " + mn.toString());
    }
}
