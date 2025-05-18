package org.example.xml;

import org.example.collectionClasses.Product;

import java.util.Comparator;

/**
 * Product collection comparator.
 */
public class Sorting implements Comparator<Product> {

    @Override
    public int compare(Product a, Product b) {
        if (a.equals(b))
            return 0;
        return (a.getPrice() < b.getPrice() ? -1 : 1);
    }
}