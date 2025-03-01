package org.example;

import org.example.collectionClasses.Product;
import org.example.xml.ProductXmlReader;
import org.example.xml.ProductXmlReader;

import java.util.*;

class Main{
    public static void main(String[] args){
        TreeMap<Product, Integer> result = new ProductXmlReader("Lab5").xmlRead();
        Set<Map.Entry<Product,Integer>> set = result.entrySet();
        Iterator<Map.Entry<Product,Integer>> i = set.iterator();
        while (i.hasNext()){
            Map.Entry<Product, Integer> elem = (Map.Entry<Product,Integer>)i.next();
            System.out.printf("%s : %s\n", elem.getKey(), elem.getValue());
        }
    }
}