package org.example;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import org.example.collectionClasses.Product;
import org.example.xml.Sorting;

import java.util.*;

import lombok.Data;
import org.example.collectionClasses.readers.ProductReadable;

import static java.lang.Math.random;

/**
 * A class that stores and manages a collection.
 */
@Data
@XmlRootElement(name="Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerCollection {

    /**
     * A collection of Product objects, where the TreeMap key is a Product object and the value is a random number up to 1000.
     */
    @XmlTransient
    @Getter
    private static TreeMap<Product, Integer> products = new TreeMap<>(new Sorting());

    /**
     * List needed to output the products collection using the JAXB tool.
     */
    @XmlElement(name="Product")
    private List<Product> translator = new ArrayList<>();

    /**
     * Intermediate method translating List to TreeMap.
     * @param managerCollection object ProductCollection with empty products and not empty translator.
     */
    public void copyProducts(ManagerCollection managerCollection){
        for (Product elem: managerCollection.getTranslator()){
            products.put(elem, (int)(random() * 1000));
        }
    }

    /**
     * Adds a Product object to the collection, generating a value.
     * @param add the Product object to add
     */
    public static void addProduct(Product add) {
        products.put(add, (int)(random() * 1000));
    }

    /**
     * Replaces the Product object by id.
     * @param id id of the collection object to change.
     */
    public void setId(Scanner scanner, int id) {
        Product curIdProduct = findId(id);
        Product newProduct;
        if (curIdProduct == null){
            newProduct = ProductReadable.readProduct(scanner);
        }else{
            newProduct = ProductReadable.readProduct(scanner, id);
            products.remove(curIdProduct);
        }
        products.put(newProduct, (int)(random() * 1000));
    }

    /**
     * Removes a collection element by id.
     * @param id
     */
    public void deleteId(int id){
        Product idProduct = findId(id);
        products.remove(idProduct);
    }

    /**
     * Removes all objects from the collection.
     */
    public void clearCollection(){
        products.clear();
    }

    /**
     * Finds a collection object by id
     * @param id id of the object to find.
     * @return the Product element of the collection with the corresponding id. May be null.
     */
    public Product findId(int id){
        Iterator<Map.Entry<Product, Integer>> i = products.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (elem.getKey().getId() == id){
                return elem.getKey();
            }
        }
        return null;
    }

    /**
     * Creates a List to output the collection to a xml file.
     * @param tmp Set of collection keys.
     */
    public void createOut(Set<Product> tmp){
        this.translator.clear();
        this.translator.addAll(tmp);
    }
}