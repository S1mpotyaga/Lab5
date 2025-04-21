package org.example.commands;

import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.Product;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MinByManufacturer extends Command{

    public MinByManufacturer(Application app){
        super(app, "min_by_manufacturer", "Print any collection's key, which value 'manufacturer' is minimal.");
    }

    public void execute(Scanner scanner, String[] command){
        Iterator<Map.Entry<Product, Integer>> i = ManagerCollection.getProducts().entrySet().iterator();
        boolean isMet = false;
        Product mn = ((Map.Entry<Product, Integer>) i.next()).getKey();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (elem.getKey().getOrganization().isLow(mn.getOrganization())) {
                mn = elem.getKey();
            }
        }
        System.out.println("Minimum is: " + mn.toString());
    }
}