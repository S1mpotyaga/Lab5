package org.example.xml;

import org.example.collectionClasses.Product;

import java.util.Comparator;

class Sorting implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b){
        if (a.getPrice().equals(b.getPrice())){
            return a.getName().compareTo(b.getName());
        }else{
            return (a.getPrice() < b.getPrice() ? -1 : 1);
        }
    }
}
