package org.example;

import jakarta.xml.bind.annotation.*;
import org.example.collectionClasses.Product;

import java.util.*;

import lombok.Data;
import org.example.xml.Sorting;

import static java.lang.Math.max;
import static java.lang.Math.random;

@Data
@XmlRootElement(name="Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerCollection {

    @XmlTransient
    private TreeMap<Product, Integer> products = new TreeMap<>(new Sorting());

    @XmlElement(name="Product")
    private List<Product> converter = new ArrayList<>();

    public void convertList(){
        for (Product elem: this.converter)
            products.put(elem, (int)(Math.random() * 1000));
    }

    public void setProductNumber(){
        int mx = -1;
        for (Product current: products.keySet()){
            mx = max(current.getId(), mx);
        }
        Product.setProductNumber(mx);
    }

    public void addProduct(Product add) {
        products.put(add, (int)(random() * 1000));
    }

    public void setId(int id, Product product){
        this.deleteId(id);
        this.addProduct(product);
    }

    public void deleteId(int id){
        Product idProduct = findId(id);
        if (idProduct != null) {
            products.remove(idProduct);
        }
    }

    public void clearCollection(){
        products.clear();
    }

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
}