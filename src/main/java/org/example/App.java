package org.example;

import org.example.collectionClasses.Product;
import java.util.TreeMap;
import lombok.Data;

@Data
public class App {

    private TreeMap<Product, Integer> products;
}