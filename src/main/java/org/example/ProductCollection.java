package org.example;

import org.example.collectionClasses.Product;

import java.util.TreeMap;
import lombok.Data;
import org.example.xml.ProductXmlReader;

@Data
public class ProductCollection {
    private TreeMap<Product, Integer> products;

    public ProductCollection(){
        ProductXmlReader tmp = new ProductXmlReader("Lab5");
        this.products = tmp.xmlRead();
    }
}