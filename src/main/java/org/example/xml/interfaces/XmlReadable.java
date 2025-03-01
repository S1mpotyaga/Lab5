package org.example.xml.interfaces;

import org.example.collectionClasses.Product;

import java.util.TreeMap;

public interface XmlReadable<T> {

    public TreeMap<Product, Integer> xmlRead();
}
