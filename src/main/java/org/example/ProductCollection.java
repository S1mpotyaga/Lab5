package org.example;

import org.example.collectionClasses.Product;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.example.xml.ProductXmlReader;

import javax.xml.bind.annotation.*;

import static java.lang.Math.random;

@Data
@XmlRootElement(name="Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCollection {

    @XmlTransient
    private TreeMap<Product, Integer> products;

    @XmlElement(name="Product")
    private List<Product> out = new ArrayList<>();

    public ProductCollection() {
        ProductXmlReader tmp = new ProductXmlReader("Lab5");
        this.products = tmp.xmlRead();
    }

    public void addProduct(Product add) {
        this.products.put(add, (int)(random() * 1000));
    }

    public void setId(int id, Product newProduct) {
        if (this.products.containsKey(newProduct)) {
            this.products.replace(newProduct, id);
        } else {
            addProduct(newProduct);
        }
    }

    public void deleteId(int id){
        Product idProduct = findId(id);
        this.products.remove(idProduct);
    }

    public void clearCollection(){
        this.products.clear();
    }

    public Product findId(int id){
        Iterator<Map.Entry<Product, Integer>> i = this.products.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (elem.getKey().getId() == id){
                return elem.getKey();
            }
        }
        return null;
    }

    public void createOut(Set<Product> tmp){
        this.out.addAll(tmp);
    }
}